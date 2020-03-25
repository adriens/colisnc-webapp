package nc.opt.colisnc.app.authentification.service.impl;

import nc.opt.colisnc.app.authentification.*;
import nc.opt.colisnc.app.authentification.domain.OptUser;
import nc.opt.colisnc.app.authentification.service.AuthenticationService;
import nc.opt.colisnc.app.authentification.service.JwtService;
import nc.opt.colisnc.app.providers.AbstractProvider;
import nc.opt.colisnc.app.providers.OAuth2Token;
import nc.opt.colisnc.app.user.FavorisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private JwtService jwtManager;

	//@Autowired
	//private GeodeUserDetailsManager geodeUserDetailsManager;

	//@Autowired
	//private LdapRepository ldapRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private ProvidersFactory providersFactory;
	
	@Autowired
	private GeodeUserService geodeUserService;

	@Autowired
	private FavorisRepository favRepo;
	
	/*@Override
	public GeodeToken authenticate(final String login, final String password) {
		LOGGER.debug("Authentification LDAP : {} - {}", login, password);
		LdapUser ldapUser = ldapRepository.findByEMail(login);
		
		if(! ldapRepository.isRightPassword(ldapUser, password)) {
			throw new BadAuthenticationException("Le mot de passe n'est pas valide.");
		}
		LOGGER.debug("Utilisateur LDAP accepté.");
		
		LOGGER.debug("Récupération de l'utilisateur Geode.");
		IGeodeUserEntity entity = geodeUserRepository.getGeodeUser(ldapUser.getUid(), StringUtils.EMPTY, ldapUser.getEmail(), "LDAP");
		GeodeUser geodeUser = new GeodeUser(entity.getId(), entity.getName(), entity.getEmail(), entity.getProvider(), StringUtils.EMPTY, Arrays.asList(new SimpleGrantedAuthority(UserAuthentication.ROLE_USER)));

		LOGGER.debug("Authentification terminé, création et envoie du JWT");
		return new GeodeToken(geodeUser.getUsername(), jwtManager.createTokenForUser(geodeUser));
	}*/
	
	@Override
	public GeodeToken authenticateByProvider(final String providerName, final String code){
		
		AbstractProvider provider = providersFactory.getProvider(providerName);
				
		OAuth2Token token = providerRepository.askForToken(provider, code);
		LOGGER.debug("Token : {}", token.toString());
		
		Profil profil = providerRepository.askForProfil(provider, token);
		LOGGER.debug("Profil : {}", profil.toString());
		LOGGER.debug("Utilisateur issue du provider accepté.");
		
		LOGGER.debug("Récupération de l'utilisateur Geode.");
		//IGeodeUserEntity entity = geodeUserRepository.getGeodeUser(profil.getSub(), profil.getName(), profil.getEmail(), provider.getName());
		//GeodeUser geodeUser = new GeodeUser(entity.getId(), entity.getName(), entity.getEmail(), entity.getProvider(), StringUtils.EMPTY, Arrays.asList(new SimpleGrantedAuthority(UserAuthentication.ROLE_USER)));

		GeodeUser geodeUser = geodeUserService.getGeodeUser(profil.getSub(), profil.getName(), profil.getEmail(), provider.getName(), profil.getPicture());
		OptUser optUser = geodeUserService.getOptUser(geodeUser.getId());

		//List<String> favoris = favRepo.findAllByUserId(geodeUser.getUsername()).stream().map(f -> f.getNum()).collect(Collectors.toList());
		LOGGER.debug("Authentification terminé, création et envoie du JWT");
		return new GeodeToken(optUser, jwtManager.createTokenForUser(geodeUser));
	}

	/*@Override
	public UserAuthentication getUserAuthentication(String headerAuthorization) {
	
		try{
			String token = jwtManager.extractBearerToken(headerAuthorization);
			LOGGER.debug("JWT extrait : {}", token);
			
			final GeodeUser geodeUser = (GeodeUser) geodeUserDetailsManager.loadUserByUsername(token);
			if (geodeUser != null) {
				LOGGER.debug("Utilisateur identifie: {}", geodeUser.toString());
				return new UserAuthentication(geodeUser);
			}
			LOGGER.debug("Utilisateur inconnu");
			return null;
		}catch(ManagerException | IllegalArgumentException e){
			LOGGER.warn(e.getMessage());
			return null;
		}
	}*/
}
