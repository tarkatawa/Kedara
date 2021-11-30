package umn.ac.keadaanudara.Model;

import java.io.Serializable;

public class City implements Serializable {
    private String kabko;
    private String lat;
    private String lon;

    public City(String kabko, String lat, String lon) {
        this.kabko = kabko;
        this.lat = lat;
        this.lon = lon;
    }

    public String getKabko() {
        return kabko;
    }

    public void setKabko(String kabko) {
        this.kabko = kabko;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
