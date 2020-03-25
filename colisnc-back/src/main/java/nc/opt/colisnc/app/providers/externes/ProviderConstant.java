package nc.opt.colisnc.app.providers.externes;

/**
 * <p>Constantes pour la construction des URL OpenId</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public interface ProviderConstant {
	
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
		}
	}
	
	/** Construction de l'url de demande d'un token */
	interface Token{
		
		interface Param{
			String CODE = "code";
			String REDIRECT_URI = "redirect_uri";
			String CLIENT_ID = "client_id";
			String CLIENT_SECRET = "client_secret";
			String GRANT_TYPE = "grant_type";
		}
		
		interface Value{
			String GRANT_TYPE = "authorization_code";
		}
	}
	
	interface Profil{
		
		interface Param{
			String ACCESS_TOKEN = "access_token";
			String FIELDS = "fields";
			String FORMAT = "format";
		}
		
		interface Value{
 			String EMAIL = "email";
 			String ID = "id";
 			String NAME = "name";
 			String JSON = "json";
		}
	}

}
