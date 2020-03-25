package nc.opt.colisnc.app.providers;

/**
 * <p>Informations communes à tous les providers</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractProvider {

	protected String name;
	protected String id;
	protected CodeTypeProvider type;
	protected String secret;
	protected String urlCallback;
	protected String urlCode;
	protected String urlToken;
	
	public AbstractProvider(CodeTypeProvider type) {
		if(type == null) {
			throw new AssertionError("Le type du provider est inconnu");
		}
		this.type = type;
	}
	
	/** @return l'URL de callback */ 
	public String getUrlCallback() {
		return urlCallback;
	}
	public void setUrlCallback(String urlCallback) {
		this.urlCallback = urlCallback;
	}
	
	/** @return l'identifiant du provider */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/** @return le {@link CodeTypeProvider} du provider */
	public CodeTypeProvider getType() {
		return type;
	}
	public void setType(CodeTypeProvider type) {
		this.type = type;
	}
	
	/** @return le secret du provider */
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
		
	/** @return le nom du provider */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/** @return l'url d'authorisation pour la demande d'un code */
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	/** @return l'url de demande d'un token via un code */
	public String getUrlToken() {
		return urlToken;
	}
	public void setUrlToken(String urlToken) {
		this.urlToken = urlToken;
	}
	
	@Override
	public String toString() {
		return "AbstractProvider [name=" + name + ", id=" + id + ", secret=" + secret + ", urlCallback=" + urlCallback
				+ ", urlCode=" + urlCode + ", urlToken=" + urlToken + "]";
	}
}
