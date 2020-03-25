package nc.opt.colisnc.app.providers.openid;

import nc.opt.colisnc.app.providers.OAuth2Token;

/**
 * <p>Represente un Id Token</p>
 * <p>Token contenant les informations d'authentification d'un utilisateur, envoyé par un serveur d'autorisation</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class OpenIdToken extends OAuth2Token {
	
	private String id_token;
	
	//TODO Ajouter la definition des champs
	/**
	 * <p>Information contenu dans l'id token :</p>
	 * <ul>
	 * <li>iss (Obligatoire) : identifiant pour l'emetteur de la reponse</li>
	 * <li>sub (Obligatoire) : </li>
	 * <li>aud (Obligatoire) : </li>
	 * <li>exp (Obligatoire) : </li>
	 * <li>iat (Obligatoire) : </li>
	 * <li>auth_time (Obligatoire) : </li>
	 * <li>nonce : </li>
	 * <li>acr : </li>
	 * <li>amr : </li>
	 * <li>azp : </li>
	 * </ul>
	 * 
	 * @return l'id token
	 */
	public String getId_token() {
		return id_token;
	}
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}
	
	@Override
	public String toString() {
		return "OpenIdToken [id_token=" + id_token + ", access_token=" + access_token + ", expires_in=" + expires_in
				+ ", token_type=" + token_type + "]";
	}
}
