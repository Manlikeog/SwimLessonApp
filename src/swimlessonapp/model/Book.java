package swimlessonapp.model;

public class Book {
    private static int lastId = 0;
    private Learner learner;
    private Lesson lesson;
    private String status;
    private String review;
    private int week;
    private int rating;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    private int month;

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Book(Learner learner, Lesson lesson) {
        this.learner = learner;
        this.lesson = lesson;
        this.status = "booked";
        this.review = "";
        this.rating = 0;
        this.month = 2;
        this.week = 4;
        setId(++lastId);
    }

    public int getId() {
        return id;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReview(String review) {
        this.review = review;
    }
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    // Constructor, getters, setters
}