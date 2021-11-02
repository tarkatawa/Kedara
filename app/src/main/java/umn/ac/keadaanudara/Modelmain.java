package umn.ac.keadaanudara;

import java.io.Serializable;

public class Modelmain implements Serializable {

    private String time = null;
    private double currentTemp = 0.0;
    private String icon = null;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
