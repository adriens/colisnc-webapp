package nc.opt.colisnc.app.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Favoris {

    @Id
    private String id;
    private String userId;
    private String num;

    public Favoris() { }

    public Favoris(String id, String userId, String num) {
        this.id = id;
        this.userId = userId;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
