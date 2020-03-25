package nc.opt.colisnc.app.providers.openid;

/**
 * <p>Constantes pour la construction des URL OpenId</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public interface OpenIdConstants {
	
	/** Construction de l'url d'autorisation */
	interface Authorization{
		
		interface Param{
			String SCOPE = "scope";
			String RESPONSE_TYPE = "response_type";
			String CLIENT_ID = "client_id";
			String REDIRECT_URI = "redirect_uri";
			String STATE = "state";
		}
		
		interface Value{
			String RESPONSE_TYPE_CODE = "code";
			String SCOPE = "openid%20email%20profile";
		}
	}
	
	/** Construction de l'url de demande d'un token */
	interface Token{
		
		interface Param{
			String GRANT_TYPE = "grant_type";
			String CODE = "code";
			String REDIRECT_URI = "redirect_uri";
			String CLIENT_ID = "client_id";
			String CLIENT_SECRET = "client_secret";
		}
		
		interface Value{
			String GRANT_TYPE = "authorization_code";
		}
	}
}
