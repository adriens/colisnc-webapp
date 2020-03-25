package nc.opt.colisnc.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris, String> {

    public List<Favoris> findAllByUserId(String userid);

    public Favoris findByUserIdAndNum(String userid, String num);
}
