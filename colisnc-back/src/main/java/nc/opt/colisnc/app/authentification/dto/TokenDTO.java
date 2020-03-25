package nc.opt.colisnc.app.authentification.dto;

import java.util.List;

/**
 * <p>DTO pour stocker un JWT et un nom d'utilisateur</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class TokenDTO {

	private String token;
	private String username;
	private List<String> favoris;
	
	/** @return le JWT */
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	/** @return le nom d'utilisateur */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getFavoris() {
		return favoris;
	}

	public void setFavoris(List<String> favoris) {
		this.favoris = favoris;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + ", username=" + username + "]";
	}
}
