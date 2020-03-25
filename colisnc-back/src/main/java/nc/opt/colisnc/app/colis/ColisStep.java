package nc.opt.colisnc.app.colis;

import java.beans.Transient;
import java.time.LocalDateTime;

public class ColisStep {

    private String id;
    private String pays;
    private String localisation;
    private String typeEvenement;
    private LocalDateTime date;
    private Double lat;
    private Double lng;

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getPays() { return pays; }
    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLocalisation() {
        return localisation;
    }
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double getLng() { return lng; }
    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getTypeEvenement() { return typeEvenement; }
    public void setTypeEvenement(String typeEvenement) { this.typeEvenement = typeEvenement; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public boolean isLocValid() { return lat != 0 && lng != 0; }

    @Override
    public String toString() {
        return "ColisStep{" +
                "id='" + id + '\'' +
                ", pays='" + pays + '\'' +
                ", localisation='" + localisation + '\'' +
                ", typeEvenement='" + typeEvenement + '\'' +
                ", date=" + date +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
