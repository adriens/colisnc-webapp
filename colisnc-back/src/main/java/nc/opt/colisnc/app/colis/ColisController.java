package nc.opt.colisnc.app.colis;

import nc.opt.colisnc.app.user.Favoris;
import nc.opt.colisnc.app.user.FavorisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class ColisController {

    private final Logger log = LoggerFactory.getLogger(ColisController.class);

    @Autowired
    private ColisService colisService;

    @Autowired
    private FavorisRepository repo;

    public ColisController() { }

    @GetMapping("/colis/{id}")
    public ResponseEntity<List<ColisStep>> getColis(@PathVariable String id, Principal principal) {

        List<ColisStep> steps = colisService.getColis(id);

        if (principal != null && steps != null && steps.size() > 0) {
            Favoris favoris = repo.findByUserIdAndNum(principal.getName(), id);
            if (favoris == null) {
                repo.save(new Favoris(UUID.randomUUID().toString(), principal.getName(), id));
            }
        }
        return new ResponseEntity(steps, HttpStatus.OK);
    }
}
