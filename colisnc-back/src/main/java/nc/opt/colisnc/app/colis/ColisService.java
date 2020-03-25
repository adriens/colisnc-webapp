package nc.opt.colisnc.app.colis;

import com.adriens.github.colisnc.colisnc.ColisCrawler;
import com.adriens.github.colisnc.colisnc.ColisDataRow;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
public class ColisService {


    public ColisService() { }

    public List<ColisStep> getColis(String id) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        Map<String, List<Double>> locs = new HashMap<>();

        try {
            List<ColisDataRow> coliadDetails = ColisCrawler.getColisRows(id);
            List<ColisStep> result = coliadDetails.stream().map(c -> findLoc(mapper.convertValue(c, ColisStep.class), locs)).collect(Collectors.toList());
            result.forEach(r -> r.setId(UUID.randomUUID().toString()));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ColisStep findLoc(ColisStep step, Map<String, List<Double>> locs) {

        List<Double> loc = locs.get(step.getLocalisation() + " " + step.getPays());

        if(loc != null && loc.size() > 0){
            step.setLat(loc.get(0));
            step.setLng(loc.get(1));
        } else {

            try {
                GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCytoaEdO3faTAGREnxEyLvMqHPxzVBhuM").build();
                GeocodingResult[] results = GeocodingApi.geocode(context, step.getLocalisation() + " " + step.getPays()).await();

                if (results.length > 0) {
                    step.setLat(results[0].geometry.location.lat);
                    step.setLng(results[0].geometry.location.lng);
                    locs.put(step.getLocalisation() + " " + step.getPays(), Arrays.asList(results[0].geometry.location.lat, results[0].geometry.location.lng));
                } else {
                    step.setLng(0D);
                    step.setLat(0D);
                    locs.put(step.getLocalisation() + " " + step.getPays(), Arrays.asList(0D, 0D));
                }
            } catch (Exception e) {
                System.out.println(step.toString());
                e.printStackTrace();
            }
        }
        return step;
    }
}
