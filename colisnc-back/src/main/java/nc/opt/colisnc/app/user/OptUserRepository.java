package nc.opt.colisnc.app.user;

import nc.opt.colisnc.app.authentification.domain.OptUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OptUserRepository extends JpaRepository<OptUser, String> { }