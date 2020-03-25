package nc.opt.colisnc.app.providers;

/**
 * <p>Token OAuth2 renvoyé par les providers</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractToken {
	
	protected String access_token;
	protected String expires_in;
	protected String token_type;
			
	/** @return un Access token permettant d’accéder aux données des utilistateurs */
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/** @return le temps en secondes après lequel le “bearer token” sera invalide */
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
	/** @return le type du token. Ce paramètre sera toujours “Bearer” */
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	@Override
	public String toString() {
		return "OAuth2Token [access_token=" + access_token + ", expires_in=" + expires_in + ", token_type=" + token_type
				+ "]";
	}
}
