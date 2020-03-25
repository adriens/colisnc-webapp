package nc.opt.colisnc.app.authentification;

import nc.opt.colisnc.app.authentification.domain.OptUser;

import java.util.List;

/**
 * <p>Ecapsulation d'un token JWT avec le nom de l'utilisateur associé</p>
 *
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class GeodeToken {

	private String token;
	private OptUser user;

	public GeodeToken(OptUser user, String token) {
		this.user = user;
		this.token = token;
	}

	/** @return le token JWT */
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	/** @return le nom de l'utilisateur associé au JWT */
	public OptUser getUser() {
		return user;
	}
	public void setUsername(OptUser user) {
		this.user = user;
	}
}
