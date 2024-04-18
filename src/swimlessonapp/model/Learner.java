package swimlessonapp.model;

public class Learner {

    private static int lastId = 0; // 15 pre-registered learners
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final char gender;
    private final int age;
    private final String emergencyContact;
    private int currentGradeLevel;

    public Learner(String firstName, String lastName, char gender, int age, String emergencyContact, int currentGradeLevel, int userId) {
        this.userId =  ++lastId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public char getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
    public String getEmergencyContact() {
        return emergencyContact;
    }
    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }
    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }
}
