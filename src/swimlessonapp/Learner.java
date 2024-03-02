package swimlessonapp;

import java.util.Random;

public class Learner {
    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emergencyContact;

    public Learner(String firstName, String lastName, String gender, int age, String emergencyContact) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setAge(age);
        this.setEmergencyContact(emergencyContact);
        this.setUserId(generateUserId());
    }

    public Learner() {
        this.userId = generateUserId();
        this.firstName = "timi";
        this.lastName = "oguntade";
        this.age = 6;
        this.emergencyContact = "0000000000";
    }
    Config config = new Config();

    //Method to generate a unique user id
    private int generateUserId() {
        Random random = new Random();
        return random.nextInt(100); // Generate a random integer as user ID
    }

    //Method for user details
    private void learnerDetailsInput() {
        firstName = config.stringInput("Enter first name: ");
        lastName = config.stringInput("Enter last name: ");
        gender = config.stringInput("Enter gender: ");
        age = config.intInput("Enter age: ");
        emergencyContact = config.stringInput("Enter emergency contact number: ");
    }

    //Method to check if the age is within allowed range
    public boolean checkAge() {
        return (age >= 4 && age <= 11);
    }

    //Method to ensure the emergency contact number is correct
    public boolean checkEmergencyContact() {
        return emergencyContact.matches("\\d{10}");
    }

    //Method to register a new learner
    public void registerNewLearner() {
        learnerDetailsInput();
        if (!checkAge()) {
            config.stringOutput("Age must be between 4 and 11 years old.");
            return;
        }
        if (!checkEmergencyContact()) {
            config.stringOutput("Invalid emergency contact number format. It must be a 10-digit number.");
            return;
        }

        // Check if the learner already exists
        if (Data.isLearnerRegistered(this)) {
            config.stringOutput("User already exists!");
        } else {
            Data.addLearner(this);
            config.stringOutput("Hurray!!! " + firstName + " " + lastName + " you have been registered with Hatfield junior swimming lesson");
            Data.printRegisteredLearners();
        }
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getEmergencyContact() {return emergencyContact;}
    public void setEmergencyContact(String emergencyContact) {this.emergencyContact = emergencyContact;}
    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}
}