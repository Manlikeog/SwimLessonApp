package swimlessonapp.model;

import static swimlessonapp.Config.generateUserId;

public class Learner {
    private  int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emergencyContact;
    private int currentGradeLevel;

    public Learner(int userId, String firstName, String lastName, String gender, int age, String emergencyContact, int currentGradeLevel) {
        this.userId = userId;
        this.setFirstName(firstName);
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
    }

    public Learner() {
        this.userId = generateUserId();
        this.firstName = "timi";
        this.lastName = "oguntade";
        this.age = 6;
        this.emergencyContact = "0000000000";
        this.gender = "Male";
        this.currentGradeLevel = 8;
    }
    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }
}
