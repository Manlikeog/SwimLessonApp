package swimlessonapp.model;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private static int lastId = 0;
    private final int id;
    private final String day;
    private final String time;
    private final int gradeLevel;

    private final Coach coach;
    private final List<Learner> learners;
    private final int maxLearners;

    public Lesson(String day, String time, int gradeLevel, Coach coach) {
        this.id = ++lastId;
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.learners = new ArrayList<>();
        this.maxLearners = 4;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
    public int getGradeLevel() {
        return gradeLevel;
    }
    public Coach getCoach() {
        return coach;
    }
    public List<Learner> getLearners() {
        return learners;
    }

    public void addLearner(Learner learner) {
        if (learners.size() < maxLearners ) {
            learners.add(learner);
        } else {
            System.out.println("Lesson is full. Cannot add more learners.");
        }
    }
    public void removeLearner(Learner learner) {
        learners.remove(learner);
    }

    public boolean isLearnerEnrolled(Learner learner) {
        return !learners.contains(learner);
    }

    public int getMaxLearners() {
        return maxLearners;
    }
}
