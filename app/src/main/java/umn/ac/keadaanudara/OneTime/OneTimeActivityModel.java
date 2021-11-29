package umn.ac.keadaanudara.OneTime;

public class OneTimeActivityModel {
    private String activity;
    private String location;
    private String date;
    private String time;
    private int reminders;

    public OneTimeActivityModel(String activity, String location, String date, String time, int reminders) {
        this.activity = activity;
        this.location = location;
        this.date = date;
        this.time = time;
        this.reminders = reminders;
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
}
