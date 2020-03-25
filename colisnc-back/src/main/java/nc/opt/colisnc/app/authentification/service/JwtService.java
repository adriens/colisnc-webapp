package nc.opt.colisnc.app.authentification.service;

import nc.opt.colisnc.app.authentification.GeodeUser;
import nc.opt.colisnc.app.exceptions.ManagerException;

/**
 * <p>Interface exposant les méthodes liées à la gestion des JWT</p>
 *
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public interface JwtService {

	/**
	 * <p>Extraction du token</p>
	 * <p>On récupère uniquement des JWT de type Baerer</p>
	 * 
	 * @param headerAuthorization : la chaine de caractère contenant le JWT
	 * @return le JWT
	 * @throws ManagerException si le type de JWT est different de Bearer
	 * @throws IllegalArgumentException si le param est null ou vide
	 */
	String extractBearerToken(String headerAuthorization) throws ManagerException, IllegalArgumentException;
	
	/**
	 * <p>Parse un JWT et récupère le {@link GeodeUser} associé</p>
	 * 
	 * @param token : le token JWT
	 * @return : le {@link GeodeUser}
	 * @throws ManagerException s'il y a des erreurs lors du parse 
	 * @throws IllegalArgumentException si le param est null ou vide
	 */
	GeodeUser parseUserFromToken(String token) throws ManagerException, IllegalArgumentException;

	/**
	 * <p>Création d'un JWT associé à un {@link GeodeUser}</p>
	 * 
	 * @param user : le {@link GeodeUser}
	 * @return le JWT
	 * @throws IllegalArgumentException si le user est null ou si le username n'est pas renseigné
	 */
	String createTokenForUser(GeodeUser user) throws IllegalArgumentException;
}
