package nc.opt.colisnc.app.authentification.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class OptUser {

    @Id
    private String id;
    private String email;
    private String provider;
    private String name;
    private String picture;

    @Transient
    private List<String> favoris;

    public OptUser() { }

    public OptUser(String id, String name, String email, String provider, String picture) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public List<String> getFavoris() {
        return favoris;
    }

    public void setFavoris(List<String> favoris) {
        this.favoris = favoris;
    }
}
