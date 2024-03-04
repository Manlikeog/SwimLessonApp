package swimlessonapp;

import java.util.ArrayList;
import java.util.Objects;

public class Data {
    private static final ArrayList<Learner> listOfLearners = new ArrayList<>();

    static {
        // static list of learners
        listOfLearners.add(new Learner( "John", "Doe", "Male", 8, "1234567890"));
        listOfLearners.add(new Learner( "Alice", "Smith", "Female", 10, "9876543210"));
    }

    // Method to add a learner to the list of registered learners
    public static void addLearner(Learner learner) {
        listOfLearners.add(learner);
    }

    // Method to check if a learner is already registered
    public static boolean isLearnerRegistered(Learner newlearner) {
        return listOfLearners.stream().anyMatch(learner ->
                Objects.equals(learner.getFirstName(), newlearner.getFirstName()) &&
                        Objects.equals(learner.getLastName(), newlearner.getLastName()));
    }

    // Method to print the ArrayList of registered learners
    public static void printRegisteredLearners() {
        System.out.println("Registered Learners:");
        listOfLearners.forEach(learner -> System.out.println("User ID: " + learner.getUserId() +
                ", Name: " + learner.getFirstName() + " " + learner.getLastName() +
                ", Gender: " + learner.getGender() +
                ", Age: " + learner.getAge() +
                ", Emergency Contact Number: " + learner.getEmergencyContact()));
    }

    public static ArrayList<Learner> getListOfLearners() {
        return listOfLearners;
    }
}