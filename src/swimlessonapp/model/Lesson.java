package swimlessonapp.model;

import java.util.Arrays;

public class Lesson {
    private String day;
    private String time;
    private String gradeLevel;
    private Coach coach;
    private Learner[] learners;
    private boolean[] attendance;
    private int[] ratings;

    public Lesson(String day, String time, String gradeLevel, Coach coach, int maxLearners) {
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.learners = new Learner[maxLearners];
        this.attendance = new boolean[maxLearners];
        this.ratings = new int[maxLearners];
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Learner[] getLearners() {
        return learners;
    }

    public boolean[] getAttendance() {
        return attendance;
    }

    public int[] getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", gradeLevel='" + gradeLevel + '\'' +
                ", coach=" + coach.getName() +
                '}';
    }
}