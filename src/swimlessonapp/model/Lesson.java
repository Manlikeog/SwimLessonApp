package swimlessonapp.model;

public class Lesson {
    private String day;
    private String time;
    private String gradeLevel;
    private Coach coach;
    private Learner[] learners;
    private int maxLearners;

    public Lesson(String day, String time, String gradeLevel, Coach coach) {
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.learners = new Learner[maxLearners];
       this.maxLearners = 4;
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

    @Override
    public String toString() {
        return "Lesson{" +
                "day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", gradeLevel='" + gradeLevel + '\'' +
                ", coach=" + coach.getName() +
                '}';
    }

    public void addLearner(Learner learner) {
        for (int i = 0; i < learners.length; i++) {
            if (learners[i] == null) {
                learners[i] = learner;
                return;
            }
        }
    }

    public boolean removeLearner(Learner learner) {
        for (int i = 0; i < learners.length; i++) {
            if (learners[i] != null && learners[i].equals(learner)) {
                learners[i] = null;
                return true;
            }
        }
        return false;
    }


    public boolean isLearnerEnrolled(Learner learner) {
        for (Learner enrolled : learners) {
            if (enrolled != null && enrolled.equals(learner)) {
                return true;
            }
        }
        return false;
    }

    public int getMaxLearners() {
        return maxLearners;
    }
}