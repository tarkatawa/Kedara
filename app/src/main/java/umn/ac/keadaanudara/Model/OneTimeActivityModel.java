package umn.ac.keadaanudara.Model;

public class OneTimeActivityModel {
    private String activity;
    private String location;
    private String date;
    private String time;
    private String reminders;
    private Double lon, lat;

    public OneTimeActivityModel(String activity, String location, String date, String time, String reminders, Double lon, Double lat) {
        this.activity = activity;
        this.location = location;
        this.date = date;
        this.time = time;
        this.reminders = reminders;
        this.lon = lon;
        this.lat = lat;
    }

    public OneTimeActivityModel(){}

    @Override
    public String toString() {
        return "OneActivityModel{" +
                "activity='" + activity + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", reminders=" + reminders +
                ", lon='" + lon + '\'' +
                ", lat=" + lat +
                '}';
    }

    public String getActivity() {
        return activity;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReminders() {
        return reminders;
    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReminders(String reminders) {
        this.reminders = reminders;
    }

    public void setLon(Double lon) { this.lon = lon; }

    public void setLat(Double lat) { this.lat = lat; }
}
