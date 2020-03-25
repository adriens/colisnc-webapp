package nc.opt.colisnc.app.authentification;

/**
 * <p>Informations utilisateur retourné par le provider</p>
 * 
 * @author RedStone
 * @version 1.0
 * @Since 1.0
 */
public class Profil {
	
	private String sub;
	private String name;
	private String email;
	private String picture;

	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/** @return l'adresse mail de l'utilisateur */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() { return picture; }
	public void setPicture(String picture) { this.picture = picture; }

	@Override
	public String toString() {
		return "Profil [email=" + email + "]";
	}
}
