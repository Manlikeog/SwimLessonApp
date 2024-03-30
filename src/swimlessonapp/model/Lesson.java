package swimlessonapp.model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    private TimeTable timeTable;
//    private String day;
//    private String time;
    private int gradeLevel;

    private Coach coach;
    private List<Learner> learners;
    private int maxLearners;

    public Lesson(TimeTable timeTable, int gradeLevel, Coach coach) {
//        this.day = day;
//        this.time = time;
        this.timeTable = timeTable;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.learners = new ArrayList<>();
        this.maxLearners = 4;
    }

//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Learner> getLearners() {
        return learners;
    }

//    @Override
//    public String toString() {
//        return "Lesson{" +
//                "day='" + day + '\'' +
//                ", time='" + time + '\'' +
//                ", gradeLevel='" + gradeLevel + '\'' +
//                ", coach=" + coach.name() +
//                '}';
//    }

    public void addLearner(Learner learner) {
        if (learners.size() < maxLearners ) {
            learners.add(learner);
        } else {
            System.out.println("Lesson is full. Cannot add more learners.");
        }
    }

    public boolean removeLearner(Learner learner) {
        return learners.remove(learner);
    }

    public boolean isLearnerEnrolled(Learner learner) {
        return learners.contains(learner);
    }

    public int getMaxLearners() {
        return maxLearners;
    }
}
