package nc.opt.colisnc.app.authentification;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>Implementation de l'{@link Authentication} Spring Security</p>
 * 
 * @See {@link Authentication}
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class UserAuthentication implements Authentication {

	/** serialVersionUID */
	private static final long serialVersionUID = -7950621089769805833L;
	
	public static final String
            ROLE_USER = "ROLE_USER";
	
	private final User user;
    private boolean authenticated = true;

    public UserAuthentication(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public User getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
