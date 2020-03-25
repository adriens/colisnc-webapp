package nc.opt.colisnc.app.utils;

import nc.opt.colisnc.app.exceptions.RepositoryException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpUtils {
	
	/**
	 * <p>Lit la reponse HTTP et la retourne sous forme de String</p>
	 * 
	 * @param httpResponse : la {@link HttpResponse}
	 * @return le contenu de la reponse sous forme de String (Non null)
	 */
	public static String getResponse(final HttpResponse httpResponse){
		
		HttpEntity httpEntity = httpResponse.getEntity();

		try (InputStream inputStream = httpEntity.getContent();
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.displayName()));) {
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			return response.toString();
			
		} catch(IOException e){
			throw new RepositoryException("Impossible de lire le contenu de la reponse HTTP", e);
		}
	}
}
