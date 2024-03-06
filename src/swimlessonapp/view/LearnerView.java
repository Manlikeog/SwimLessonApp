package swimlessonapp.view;

import swimlessonapp.Config;

import swimlessonapp.controllers.LearnerController;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;

import java.util.Scanner;

public class LearnerView {

    static Config config = new Config();
    static Learner newLearner = new Learner();

    public static Learner learnerDetailsInput() {
        String firstName = config.stringInput("Enter first name: ").toUpperCase();
        newLearner.setFirstName(firstName);
        String lastName = config.stringInput("Enter last name: ").toUpperCase();
        newLearner.setLastName(lastName);
        String gender = config.stringInput("Enter gender: ");
        newLearner.setGender(gender);
        int age = config.intInput("Enter age: ");
        newLearner.setAge(age);
        String emergencyContact = config.stringInput("Enter emergency contact number: ");
        newLearner.setEmergencyContact(emergencyContact);

        return newLearner;
    }

    public static void printRegisteredLearners(LearnerRepository storedLearners) {
        System.out.println("Registered Learners:");
        storedLearners.getAllLearners().forEach(learner -> System.out.println("User ID: " + learner.getUserId() +
                ", Name: " + learner.getFirstName() + " " + learner.getLastName() +
                ", Gender: " + learner.getGender() +
                ", Age: " + learner.getAge() +
                ", Emergency Contact Number: " + learner.getEmergencyContact()));
    }
}
