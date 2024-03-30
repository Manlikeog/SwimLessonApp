package swimlessonapp.model;

public class TimeTable {

    public TimeTable(String day, String time, Coach coach) {
        this.day = day;
        this.time = time;
        this.coach = coach;
    }

    private String day;
    private String time;
    private Coach coach;
}
