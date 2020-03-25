package nc.opt.colisnc.app.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

public class MapperUtils {
	
	/**
	 * <p>Lit la reponse HTTP et la retourne sous forme de String</p>
	 * 
	 * @param httpResponse : la {@link HttpResponse}
	 * @return le contenu de la reponse sous forme de String (Non null)
	 */
	public static ObjectMapper mapper(){
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return mapper;
	}
}
