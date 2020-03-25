package nc.opt.colisnc.app.authentification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import nc.opt.colisnc.app.exceptions.ManagerException;
import nc.opt.colisnc.app.exceptions.RepositoryException;
import nc.opt.colisnc.app.providers.AbstractProvider;
import nc.opt.colisnc.app.providers.AbstractToken;
import nc.opt.colisnc.app.providers.externes.FacebookProfil;
import nc.opt.colisnc.app.providers.externes.LinkedinProfil;
import nc.opt.colisnc.app.providers.externes.Provider;
import nc.opt.colisnc.app.providers.externes.ProviderConstant;
import nc.opt.colisnc.app.providers.openid.OpenIdConstants;
import nc.opt.colisnc.app.providers.openid.OpenIdProvider;
import nc.opt.colisnc.app.providers.openid.OpenIdToken;
import nc.opt.colisnc.app.utils.HttpUtils;
import nc.opt.colisnc.app.utils.MapperUtils;
import nc.opt.colisnc.app.utils.UrlConstants;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * <p>Classe accédant aux données OpenId / oAuth2</p>
 *
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
@Repository
public class ProviderRepository {

	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(ProviderRepository.class);
	
	private HttpClient httpClient;
	
	@PostConstruct
	public void setProxy() {
		HttpHost proxy = new HttpHost("proxy-olfeo.intranet.opt", 3128);
		this.httpClient = HttpClients.custom().build();
	}

	public OpenIdToken askForToken(final AbstractProvider client, final String code) {
		checkArgument(client != null);
		checkArgument(!Strings.isNullOrEmpty(code));

		logger.debug("Requete HTTP POST pour la demande d'un token OpenId - Url = {}, Code = {}", client.getUrlToken(), code);
				
		try{	
			
			HttpPost httpPost = new HttpPost(client.getUrlToken());
			
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair(OpenIdConstants.Token.Param.CLIENT_ID, client.getId()));
			params.add(new BasicNameValuePair(OpenIdConstants.Token.Param.CLIENT_SECRET, client.getSecret()));
			params.add(new BasicNameValuePair(OpenIdConstants.Token.Param.GRANT_TYPE, OpenIdConstants.Token.Value.GRANT_TYPE));
			params.add(new BasicNameValuePair(OpenIdConstants.Token.Param.REDIRECT_URI, client.getUrlCallback()));
			params.add(new BasicNameValuePair(OpenIdConstants.Token.Param.CODE, code));
				
			logger.debug(params.toString());
			httpPost.setEntity(new UrlEncodedFormEntity(params));
						
			HttpResponse httpResponse = httpClient.execute(httpPost);
				
			if(httpResponse.getStatusLine().getStatusCode() != HttpStatus.OK.value()){
				throw new RepositoryException(httpResponse.getStatusLine().getStatusCode(), "Echec de la requete HTTP - Status = {}", httpResponse.getStatusLine().getStatusCode());
			}
			
			String result = HttpUtils.getResponse(httpResponse);
			logger.debug("Reponse HTTP : {}", result);
						
			return new Gson().fromJson(result, OpenIdToken.class);
		}catch(JsonSyntaxException e){
			throw new RepositoryException("Mauvais format de reponse pour une demande de token OpenId", e);
		}catch (UnsupportedEncodingException e) {
			throw new RepositoryException("Impossible d'encoder les paramètres de la requête", e);
		}catch (IOException e) {
			throw new RepositoryException("Impossible d'executer la requête", e);
		}
	}

	public Profil askForProfil(final AbstractProvider provider, final AbstractToken token) {
		checkArgument(provider != null);
		checkArgument(token != null);
		
		if(provider instanceof OpenIdProvider){
			
			if(token instanceof OpenIdToken){
				checkArgument(!Strings.isNullOrEmpty(((OpenIdToken) token).getId_token()));
			
				try {
					JWT jwt = JWTParser.parse(((OpenIdToken) token).getId_token());
					logger.debug(jwt.getJWTClaimsSet().toString());
					return MapperUtils.mapper().convertValue(jwt.getJWTClaimsSet().getClaims(), Profil.class);
				} catch (ParseException e) {
					throw new ManagerException("Impossible de parser le token OpenId", e);
				}
			}
			
			throw new RepositoryException();
		}
		
		if(provider instanceof Provider){
			checkArgument(!Strings.isNullOrEmpty(token.getAccess_token()));
		
			logger.debug("Requete HTTP GET pour la demande d'un profil {} - Url = {}", provider.getName(), provider.getUrlToken());
		
			try{			
				
				StringBuilder sb = new StringBuilder(((Provider) provider).getUrlProfil());
				sb.append(UrlConstants.URL_START_PARAMETER);
				sb.append(ProviderConstant.Profil.Param.ACCESS_TOKEN).append(UrlConstants.URL_EGAL).append(token.getAccess_token());
				
				if(Provider.FACEBOOK.equals(provider.getName())){
					sb.append(UrlConstants.URL_SEPARATOR).append(ProviderConstant.Profil.Param.FIELDS).append(UrlConstants.URL_EGAL).append(ProviderConstant.Profil.Value.ID+","+ProviderConstant.Profil.Value.EMAIL+","+ProviderConstant.Profil.Value.NAME);
				}
				
				if(Provider.LINKEDIN.equals(provider.getName())){
					sb.append(UrlConstants.URL_SEPARATOR).append(ProviderConstant.Profil.Param.FORMAT).append(UrlConstants.URL_EGAL).append(ProviderConstant.Profil.Value.JSON);
				}
				
				HttpGet get = new HttpGet(sb.toString());
				get.setHeader("Authorization", "Bearer " + token.getAccess_token());
				
				HttpResponse httpResponse = httpClient.execute(get);
					
				String result = HttpUtils.getResponse(httpResponse);
				logger.debug("Reponse HTTP : {}", result);
				
				if(httpResponse.getStatusLine().getStatusCode() != HttpStatus.OK.value()){
					throw new ManagerException("Echec de la requete HTTP - Status = {}", httpResponse.getStatusLine().getStatusCode());
				}
				
				ObjectMapper mapper = MapperUtils.mapper();
								
				if(Provider.LINKEDIN.equals(provider.getName())){
					mapper.addMixIn(Profil.class, LinkedinProfil.class);
					return mapper.readValue(result, Profil.class);
				}
				
				if(Provider.FACEBOOK.equals(provider.getName())){
 					mapper.addMixIn(Profil.class, FacebookProfil.class);
 					return mapper.readValue(result, Profil.class);
				}
				return mapper.readValue(result, Profil.class);
			}catch(JsonSyntaxException e){
				throw new ManagerException("Mauvais format de reponse pour une demande de profil " + provider.getName(), e);
			} catch (IOException e) {
				throw new ManagerException(e.getMessage(), e);
			}
		}
		
		throw new RepositoryException();
	}
}
