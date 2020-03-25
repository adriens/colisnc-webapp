package nc.opt.colisnc.app.authentification;

import nc.opt.colisnc.app.authentification.domain.OptUser;

/**
 * Interface permettant d'accéder aux infos des utilisateurs geode
 * @author www.redstone.nc
 *
 */
public interface GeodeUserService {
	
	/**
	 * Retourne un utilisateur geode grâce aux données fourni par les providers
	 * 
	 * @param id l'id utilisateur renseigné chez le provider
	 * @param name le nom de l'utilisateur renseigné chez le provider
	 * @param email l'email utilisateur renseigné chez le provider
	 * @param provider le provider où est renseigné l'utilisateur
	 * @return 
	 */
	//IGeodeUserEntity getGeodeUser(String id, String name, String email, String provider);
	GeodeUser getGeodeUser(String id, String name, String email, String provider, String picture);

	OptUser getOptUser(String id);

	/**
	 * 
	 * @param id l'id technique
	 * @return {@link IGeodeUserEntity}
	 */
	GeodeUser get(String id);
	
}
