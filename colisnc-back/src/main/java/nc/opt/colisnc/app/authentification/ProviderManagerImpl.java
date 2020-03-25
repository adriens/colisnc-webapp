package nc.opt.colisnc.app.authentification;

import com.google.common.base.Strings;
import static nc.opt.colisnc.app.utils.UrlConstants.*;
import nc.opt.colisnc.app.providers.AbstractProvider;
import nc.opt.colisnc.app.providers.CodeTypeProvider;
import nc.opt.colisnc.app.providers.externes.Provider;
import nc.opt.colisnc.app.providers.externes.ProviderConstant;
import nc.opt.colisnc.app.providers.openid.OpenIdConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

import static com.google.common.base.Preconditions.checkArgument;

@Service(value = ProviderManager.SERVICE_NAME)
public class ProviderManagerImpl implements ProviderManager{

	private static final Logger logger = LoggerFactory.getLogger(ProviderManagerImpl.class);

	@Autowired
	private ProvidersFactory providersFactory;

	@Override
	public String getAuthorizationUrl(final String providerName) {
		checkArgument(!Strings.isNullOrEmpty(providerName));
		logger.debug("récupération de l'url de connection pour le provider {}", providerName);
		
		AbstractProvider provider = providersFactory.getProvider(providerName);
						
		StringBuilder sb = new StringBuilder(provider.getUrlCode());
		sb.append(URL_START_PARAMETER).append(OpenIdConstants.Authorization.Param.CLIENT_ID).append(URL_EGAL).append(provider.getId());
		sb.append(URL_SEPARATOR).append(OpenIdConstants.Authorization.Param.REDIRECT_URI).append(URL_EGAL).append(provider.getUrlCallback());
		
		if(CodeTypeProvider.OPEN_ID.equals(provider.getType())){
			sb.append(URL_SEPARATOR).append(OpenIdConstants.Authorization.Param.SCOPE).append(URL_EGAL).append(OpenIdConstants.Authorization.Value.SCOPE);
			sb.append(URL_SEPARATOR).append(OpenIdConstants.Authorization.Param.RESPONSE_TYPE).append(URL_EGAL).append(OpenIdConstants.Authorization.Value.RESPONSE_TYPE_CODE);
			sb.append(URL_SEPARATOR).append(OpenIdConstants.Authorization.Param.STATE).append(URL_EGAL).append(new BigInteger(64, new SecureRandom()).toString(32));
		}
		
		if(CodeTypeProvider.OAUTH2.equals(provider.getType())){
			if(Provider.LINKEDIN.equals(provider.getName())){ //NOPMD
				sb.append(URL_SEPARATOR).append(ProviderConstant.Authorization.Param.RESPONSE_TYPE).append(URL_EGAL).append(ProviderConstant.Authorization.Value.RESPONSE_TYPE_CODE);
				sb.append(URL_SEPARATOR).append(ProviderConstant.Authorization.Param.STATE).append(URL_EGAL).append(new BigInteger(64, new SecureRandom()).toString(32));
			}

			if (Provider.TWITTER.equals(provider.getName())) {
				sb = new StringBuilder(provider.getUrlCode());
				sb.append(URL_START_PARAMETER).append("oauth_consumer_key").append(URL_EGAL).append(provider.getId());
				sb.append(URL_SEPARATOR).append("oauth_callback").append(URL_EGAL).append(provider.getUrlCallback());
			}
		}
			
		return sb.toString();
	}
		
}
