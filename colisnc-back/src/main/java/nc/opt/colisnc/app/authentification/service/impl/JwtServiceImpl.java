package nc.opt.colisnc.app.authentification.service.impl;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import nc.opt.colisnc.app.authentification.GeodeUser;
import nc.opt.colisnc.app.authentification.UserAuthentication;
import nc.opt.colisnc.app.authentification.service.JwtService;
import nc.opt.colisnc.app.exceptions.ManagerException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class JwtServiceImpl implements JwtService {

	private final static String PRIVATE_KEY = "tooManySecrets";
	private static final String AUTH_HEADER_BEARER = "Bearer";

	@Override
	public String extractBearerToken(String headerAuthorization) throws ManagerException {
		checkArgument(!Strings.isNullOrEmpty(headerAuthorization), "Aucune autorisation trouvée");
		
		if(!headerAuthorization.contains(AUTH_HEADER_BEARER)) throw new ManagerException("Le JWT doit être du type Bearer");
		
		return headerAuthorization.substring((AUTH_HEADER_BEARER + " ").length(), headerAuthorization.length());
	}
	
	@Override
	public GeodeUser parseUserFromToken(String token) {
		
		try{
			//Initialisation du parser avec la cles privée
			JwtParser parser = Jwts.parser().setSigningKey(PRIVATE_KEY);
			
			Date expire = parser.parseClaimsJws(token).getBody().getExpiration();
			if(expire == null || expire.before(new Date())) throw new ManagerException("Le JWT n'est pas valide");
			
			//Récupération du username dans les claims
			String id = parser.parseClaimsJws(token).getBody().get("id", String.class);
			String username = parser.parseClaimsJws(token).getBody().get("username", String.class);
			String email = parser.parseClaimsJws(token).getBody().get("email", String.class);
			String provider = parser.parseClaimsJws(token).getBody().get("provider", String.class);
			String picture = parser.parseClaimsJws(token).getBody().get("picture", String.class);

			List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(UserAuthentication.ROLE_USER));
			
			GeodeUser geodeUser = new GeodeUser(id, username, email, provider, StringUtils.EMPTY, picture, authorities);
			
			return geodeUser;
			
		}catch(JwtException | IllegalArgumentException e){
			throw new ManagerException("Le JWT n'est pas valide", e);
		}
	}

	@Override
	public String createTokenForUser(GeodeUser user) {
		checkArgument(user != null);
		checkArgument(!Strings.isNullOrEmpty(user.getId()));
		checkArgument(!Strings.isNullOrEmpty(user.getUsername()));
		checkArgument(!Strings.isNullOrEmpty(user.getEmail()));
		checkArgument(!Strings.isNullOrEmpty(user.getProvider()));
		
    	//Initialisation du builder JWT
    	JwtBuilder builder = Jwts.builder();
    	
    	//Ajout de la signature
    	builder.signWith(SignatureAlgorithm.HS512, PRIVATE_KEY);
    	
    	//Ajout de la date d'expiration (2 heures)
    	builder.setExpiration(new Date(new Date().getTime() + 7200000));
    	
    	//Ajout du username
    	builder.setSubject(user.getUsername());
    	
    	//Ajout des informations
    	builder.claim("id", user.getId());
    	builder.claim("username", user.getUsername());
    	builder.claim("email", user.getEmail());
    	builder.claim("provider", user.getProvider());
				
    	//Géneration du JWT
		return builder.compact();
	}
}
