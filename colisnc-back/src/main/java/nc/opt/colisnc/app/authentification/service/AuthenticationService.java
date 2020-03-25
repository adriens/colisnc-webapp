package nc.opt.colisnc.app.authentification.service;

import nc.opt.colisnc.app.authentification.GeodeToken;
import nc.opt.colisnc.app.authentification.GeodeUser;
import nc.opt.colisnc.app.authentification.UserAuthentication;

/**
 * <p>Interface exposant les méthodes d'autentification</p>
 *
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public interface AuthenticationService {

	/** Nom du paramètre d'autorisation dans le header des requetes HTTP */
	//String AUTH_HEADER_NAME = "authorization";

	/**
	 * <p>Méthode d'autentification par login/mot de passe</p>
	 * 
	 * @param login : l'identifiant
	 * @param password le mot de passe
	 * @return Un Json Web Token
	 */
	//GeodeToken authenticate(final String login, final String password);

	/**
	 * <p>Authentification d'un utilisateur dans Spring security</p>
	 * 
	 * @param providerName : le nom du provider auprès duquel on s'autentifie
	 * @param code : le code envoyé par le serveur d'autorisation
	 * @return un {@link GeodeToken} JWT valide
	 */
	GeodeToken authenticateByProvider(final String providerName, final String code);

	/**
	 * <p>Récuperation d'un {@link UserAuthentication} via le header d'une requête HTTP</p>
	 * <ul>
	 * <li>Extrait le JWT du header Authorization</li>
	 * <li>Vérifie la signature du JWT</li>
	 * <li>Associe le JWT au {@link GeodeUser} authentifié dans Spring Security</li>
	 * </ul>
	 * 
	 * @param headerAuthorization : autorisation provenant du header de la requête HTTP
	 * @return Un {@link UserAuthentication} prêt à être injecté dans le contextSecurity de Spring
	 */
	//UserAuthentication getUserAuthentication(String headerAuthorization);
}
