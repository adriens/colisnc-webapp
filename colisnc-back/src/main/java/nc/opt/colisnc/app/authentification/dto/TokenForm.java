package nc.opt.colisnc.app.authentification.dto;

/**
 * <p>Formulaire de demande de JWT</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class TokenForm {
	
	private String code;
	private String providerName;
	
	/** @return le code d'autorisation */ 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	/** @return le nom du provider */
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	@Override
	public String toString() {
		return "TokenForm [code=" + code + ", providerName=" + providerName + "]";
	}
}
