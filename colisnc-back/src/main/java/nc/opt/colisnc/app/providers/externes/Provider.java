package nc.opt.colisnc.app.providers.externes;

import nc.opt.colisnc.app.providers.AbstractProvider;
import nc.opt.colisnc.app.providers.CodeTypeProvider;

/**
 * <p>Represente un provider Externe (oAuth2 par exemple)</p>
 * <p>Le provider est chargé par configuration via un fichier YAML</p>
 * 
 * @see providers.yml
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class Provider extends AbstractProvider {
	
	public static final String LINKEDIN = "linkedin";
	public static final String FACEBOOK = "facebook";
	public static final String TWITTER = "twitter";

	private String urlProfil;
	
	public Provider() {
		super(CodeTypeProvider.OAUTH2);
	}

	/** @return l'url de demande du profil via l'access token */
	public String getUrlProfil() {
		return urlProfil;
	}
	public void setUrlProfil(String urlProfil) {
		this.urlProfil = urlProfil;
	}
	
	@Override
	public String toString() {
		return "ExterneProvider [urlCode=" + urlCode + ", urlToken=" + urlToken + ", urlProfil=" + urlProfil + ", name="
				+ name + ", id=" + id + ", secret=" + secret + ", urlCallback=" + urlCallback + "]";
	}
}
