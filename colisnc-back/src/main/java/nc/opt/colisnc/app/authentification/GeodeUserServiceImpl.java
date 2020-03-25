package nc.opt.colisnc.app.authentification;

import nc.opt.colisnc.app.authentification.domain.OptUser;
import nc.opt.colisnc.app.user.FavorisRepository;
import nc.opt.colisnc.app.user.OptUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeodeUserServiceImpl implements GeodeUserService {

    @Autowired
    private OptUserRepository repository;

    @Autowired
    private FavorisRepository favRepo;

    @Override
    public GeodeUser getGeodeUser(String id, String name, String email, String provider, String picture) {

        Optional<OptUser> result = repository.findById(id);

        if(!result.isPresent()) {
            repository.save(new OptUser(id, name, email, provider, picture));
        }

        return new GeodeUser(id, name, email, provider, "", picture, Arrays.asList());
    }

    @Override
    public OptUser getOptUser(String id) {

        Optional<OptUser> result = repository.findById(id);
        OptUser user = result.get();
        user.setFavoris(favRepo.findAllByUserId(user.getName()).stream().map(f -> f.getNum()).collect(Collectors.toList()));
        return user;
    }

    @Override
    public GeodeUser get(String id) {
        return null;
    }
}
