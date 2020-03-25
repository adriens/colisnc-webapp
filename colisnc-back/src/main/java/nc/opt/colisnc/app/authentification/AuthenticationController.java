package nc.opt.colisnc.app.authentification;

import nc.opt.colisnc.app.authentification.dto.TokenForm;
import nc.opt.colisnc.app.authentification.dto.UrlDTO;
import nc.opt.colisnc.app.authentification.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = AuthenticationController.URL_AUTHENTIFICATION)
public class AuthenticationController {
	
	public static final String URL_AUTHENTIFICATION = "/auth";
	public static final String URL_BY_PROVIDER = "/openid";
	public static final String URL_BY_LOGIN = "/user";

	@Autowired
	private ProviderManager providerManager;

	@Autowired
	private AuthenticationService authenticationManager;
	
	/**
	 * <p>Demande l'URL d'autorisation pour un provider</p>
	 * 
	 * @param providerName : le nom du provider
	 * @return l'URL
	 */
	@RequestMapping(path=URL_BY_PROVIDER + "/{provider}/authorization", method={RequestMethod.GET})
	public ResponseEntity<UrlDTO> getAuthorization(@PathVariable(value = "provider") String providerName){
		return new ResponseEntity<>(new UrlDTO(providerManager.getAuthorizationUrl(providerName)), HttpStatus.ACCEPTED);
	}
	/**
	 * Demande un jeton de validation</p>
	 *  
	 * @param form : le {@link TokenForm}
	 * @return le JWT et le nom de l'utilisateur
	 */
	@RequestMapping(path=URL_BY_PROVIDER + "/token", method={RequestMethod.POST})
    public ResponseEntity<GeodeToken> postToken(@RequestBody TokenForm form) {
		GeodeToken token = authenticationManager.authenticateByProvider(form.getProviderName(), form.getCode());
		return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
	}
	
	/**
	 * 
	 * @param form
	 * @return
	 */
	//@RequestMapping(path = URL_BY_LOGIN + "/token", method = RequestMethod.POST)
    //ResponseEntity<TokenDTO> authentication(LoginForm form);
}
