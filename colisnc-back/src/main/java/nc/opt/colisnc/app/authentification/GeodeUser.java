package nc.opt.colisnc.app.authentification;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.List;

/**
 * <p>Utilisateur Geode pour Spring Security</p>
 *
 * @See {@link User}
 * @author RedStone
 * @version 1.0
 * @since 1.0
 */
public class GeodeUser extends User {

	private static final long serialVersionUID = -3178738762581013754L;

	private String id;
	private String email;
	private String provider;
	private String picture;
	private List<String> favoris;

	public GeodeUser(String id, String username, String email, String provider, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.email = email;
		this.provider = provider;
	}

	public GeodeUser(String id, String username, String email, String provider, String password, String picture, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
		this.email = email;
		this.provider = provider;
		this.picture = picture;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) { this.id = id; }

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeodeUser other = (GeodeUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GeodeUser [id=" + id + ", email=" + email + ", provider=" + provider + "]";
	}
	
}
