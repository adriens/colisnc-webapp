package nc.opt.colisnc.app.authentification;

/**
 * 
 * @author www.redstone.nc
 *
 */
public interface IGeodeUserEntity {
	
	/**
	 * @return l'id de l'utilisateur
	 */
	String getId();
	
	/**
	 * @return le nom de l'utilisateur
	 */
	String getName();
	
	/**
	 * @return l'email de l'utilisateur
	 */
	String getEmail();
	
	/**
	 * @return le provider
	 */
	String getProvider();

}
