package nc.opt.colisnc.app.authentification;

import com.google.common.base.Strings;
import nc.opt.colisnc.app.exceptions.ProviderException;
import nc.opt.colisnc.app.providers.AbstractProvider;
import nc.opt.colisnc.app.providers.externes.Provider;
import nc.opt.colisnc.app.providers.openid.OpenIdProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * <p>Classe contenant tous les providers OpenId<p>
 * <p>Les providers sont chargés par configuration via un fichier YAML</p>
 * 
 * @See provider.yml
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "providers-config")
public class ProvidersFactory {
	
	private Map<String, OpenIdProvider> openidProviders;
	private Map<String, Provider> externeProviders;
	private String urlCallback;

	/** @return l'url de callback */
	public String getUrlCallback() {
		return urlCallback;
	}
	public void setUrlCallback(String urlCallback) {
		this.urlCallback = urlCallback;
	}
	
	/** @return une map contenant les providers OpenId */
	public Map<String, OpenIdProvider> getOpenidProviders() {
		return openidProviders;
	}
	public void setOpenidProviders(Map<String, OpenIdProvider> openidProviders) {
		this.openidProviders = openidProviders;
	}
	
	/** @return une map contenant les providers externes */
	public Map<String, Provider> getExterneProviders() {
		return externeProviders;
	}
	public void setExterneProviders(Map<String, Provider> externeProviders) {
		this.externeProviders = externeProviders;
	}
	
	public AbstractProvider getProvider(String name){
		checkArgument(!Strings.isNullOrEmpty(name));
		
		if(openidProviders != null && !openidProviders.isEmpty()){
			OpenIdProvider provider = openidProviders.get(name);
			if(provider != null){
				provider.setUrlCallback(urlCallback);
				provider.setName(name);
				checkOpenidProvider(provider);
				return provider;
			}
		}
		
		if(externeProviders != null && !externeProviders.isEmpty()){
			Provider provider = externeProviders.get(name);
			if(provider != null){
				provider.setUrlCallback(urlCallback);
				provider.setName(name);
				checkExterneProvider(provider);
				return provider;
			}
		}
		
		throw new ProviderException("Provider non pris en charge");
	}
		
	/**
	 * <p>Vérification des paramètre du provider</p>
	 * @param provider : le provider oAuth2
	 */
	private void checkExterneProvider(Provider provider){
		checkProvider(provider);
		if(Strings.isNullOrEmpty(provider.getUrlCode())) throw new ProviderException("Url d'authorisation non renseigné");
		if(Strings.isNullOrEmpty(provider.getUrlToken())) throw new ProviderException("Url de demande de token non renseigné");
		if(Strings.isNullOrEmpty(provider.getUrlProfil())) throw new ProviderException("Url de demande de profil non renseigné");
	}
	
	/**
	 * <p>Vérification des paramètre du provider</p>
	 * @param provider : le provider OpenId
	 */
	private void checkOpenidProvider(OpenIdProvider provider){
		checkProvider(provider);
		if(Strings.isNullOrEmpty(provider.getUrlCode())) throw new ProviderException("Url d'authorisation non renseigné");
		if(Strings.isNullOrEmpty(provider.getUrlToken())) throw new ProviderException("Url de demande de token non renseigné");
	}
	
	/**
	 * <p>Vérification des paramètre du provider</p>
	 * @param provider : le provider OpenId
	 */
	private void checkProvider(AbstractProvider provider){
		if(Strings.isNullOrEmpty(provider.getId())) throw new ProviderException("Id provider non renseigné");
		if(Strings.isNullOrEmpty(provider.getSecret())) throw new ProviderException("Secret provider non renseigné");
		if(Strings.isNullOrEmpty(provider.getUrlCallback())) throw new ProviderException("Url de redirection non renseigné");
	}
	
	@Override
	public String toString() {
		return "ProvidersFactory [providers=" + openidProviders + ", urlCallback=" + urlCallback + "]";
	}
}
