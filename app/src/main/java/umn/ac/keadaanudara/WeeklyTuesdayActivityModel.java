package umn.ac.keadaanudara;

public class WeeklyTuesdayActivityModel {
    private String activity;
    private String location;
    private String time;
    private int reminders;

    public WeeklyTuesdayActivityModel(String activity, String location, String time, int reminders) {
        this.activity = activity;
        this.location = location;
        this.time = time;
        this.reminders = reminders;
    }

    public WeeklyTuesdayActivityModel(){}

    @Override
    public String toString() {
        return "WeeklyTuesdayModel{" +
                "activity='" + activity + '\'' +
                ", location='" + location + '\'' +
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setReminders(int reminders) {
        this.reminders = reminders;
    }
}

