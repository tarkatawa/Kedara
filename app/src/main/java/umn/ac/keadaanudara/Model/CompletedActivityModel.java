package umn.ac.keadaanudara.Model;

public class CompletedActivityModel {
    private String activity;
    private String location;
    private String date;
    private String time;
    private int reminders;
    private Double lon, lat;

    public CompletedActivityModel(String activity, String location, String date, String time, int reminders, Double lon, Double lat) {
        this.activity = activity;
        this.location = location;
        this.date = date;
        this.time = time;
        this.reminders = reminders;
        this.lon = lon;
        this.lat = lat;
    }

    public CompletedActivityModel(){}

    @Override
    public String toString() {
        return "CompletedActivityModel{" +
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

    public int getReminders() {
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

    public void setReminders(int reminders) {
        this.reminders = reminders;
    }

    public void setLon(Double lon) { this.lon = lon; }

    public void setLat(Double lat) { this.lat = lat; }
}
