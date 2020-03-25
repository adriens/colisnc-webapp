package nc.opt.colisnc.app.authentification.dto;

/**
 * <p>Formulaire de demande d'autentification pour le LDAP</p>
 * 
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class LoginForm {
	
	private String login;
	private String password;
	
	/** @return le login */
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	/** @return le mot de passe */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "loginForm [login=" + login + ", password=" + password + "]";
	}
}
