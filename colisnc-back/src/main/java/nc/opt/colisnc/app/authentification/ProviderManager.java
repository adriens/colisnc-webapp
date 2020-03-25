package nc.opt.colisnc.app.authentification;

/**
 * <p>Interface du manager OpenId ou oAuth2</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public interface ProviderManager {
	
	/** Nom du service */
	String SERVICE_NAME = "ProviderManager";

	/**
	 * <p>Construction de l'url permettant de demander le code d'autorisation</p>
	 * 
	 * @param providerName : le nom du provider auprès duquel on s'autentifie
	 * @return l'url
	 */
	String getAuthorizationUrl(final String providerName);
}
