package umn.ac.keadaanudara.Model;

import java.io.Serializable;

public class City implements Serializable {
    private String kabko;
    private double lat;
    private double lon;
    private Boolean condition = false;

    public String getKabko() {
        return kabko;
    }

    public void setKabko(String kabko) {
        this.kabko = kabko;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }
}
