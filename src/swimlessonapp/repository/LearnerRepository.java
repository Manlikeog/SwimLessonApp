package swimlessonapp.repository;

import swimlessonapp.model.Learner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LearnerRepository {

    private final static List<Learner> listOfLearners = new ArrayList<>();
    private static LearnerRepository instance ;

    public static LearnerRepository getInstance() {
        if (instance == null) {
            instance = new LearnerRepository();
        }
        return instance;
    }

    // Add learners to the list
    static {
        listOfLearners.add(new Learner( "JOHN", "DOE", "Male", 8, "1234567890", 1));
        listOfLearners.add(new Learner( "ALICE", "SMITH", "Female", 7, "2345678901", 2));
        listOfLearners.add(new Learner( "BOB", "JOHNSON", "Male", 9, "3456789012", 3));
        listOfLearners.add(new Learner( "EMILY", "BROWN", "Female", 6, "4567890123", 4));
        listOfLearners.add(new Learner( "DAVID", "WILSON", "Male", 8, "5678901234", 5));
        listOfLearners.add(new Learner( "SOPHIA", "MARTINEZ", "Female", 7, "6789012345", 1));
        listOfLearners.add(new Learner( "MICHAEL", "ANDERSON", "Male", 9, "7890123456", 2));
        listOfLearners.add(new Learner( "OLIVIA", "TAYLOR", "Female", 6, "8901234567", 3));
        listOfLearners.add(new Learner( "EMMA", "THOMAS", "Female", 8, "9012345678", 4));
        listOfLearners.add(new Learner( "JAMES", "JACKSON", "Male", 7, "0123456789", 5));
        listOfLearners.add(new Learner( "DANIEL", "WHITE", "Male", 9, "1357902468", 1));
        listOfLearners.add(new Learner( "SOPHIE", "HARRIS", "Female", 7, "2468013579", 2));
        listOfLearners.add(new Learner( "SARAH", "MILLER", "Female", 8, "5678901234", 2));
        listOfLearners.add(new Learner( "JACOB", "LEE", "Male", 7, "6789012345", 3));
        listOfLearners.add(new Learner( "GRACE", "TAYLOR", "Female", 9, "7890123456", 4));
        listOfLearners.add(new Learner( "ETHAN", "CLARK", "Male", 6, "8901234567", 5));
        listOfLearners.add(new Learner( "LILY", "RODRIGUEZ", "Female", 8, "9012345678", 1));
        listOfLearners.add(new Learner( "NOAH", "HERNANDEZ", "Male", 7, "0123456789", 2));
        listOfLearners.add(new Learner( "AVA", "MARTINEZ", "Female", 9, "1357902468", 3));
        listOfLearners.add(new Learner( "LOGAN", "GONZALEZ", "Male", 6, "2468013579", 4));
        listOfLearners.add(new Learner( "CHLOE", "PEREZ", "Female", 8, "3579124680", 5));
        listOfLearners.add(new Learner( "MASON", "RAMIREZ", "Male", 7, "4680235791", 1));
    }

    // Get all learners
    public List<Learner> getAllLearners() {
        return listOfLearners;
    }

    // Add a learner to the list
    public  void addLearner(Learner learner) {
        listOfLearners.add(learner);
    }

    // Get a learner by user ID
    public Learner getLearnerByName(String firstName, String lastName) {
        for (Learner learner : listOfLearners) {
            if (learner.getFirstName().equalsIgnoreCase(firstName) &&
                  learner.getLastName().equalsIgnoreCase(lastName)) {
                return learner;
            }
        }
        return null;
    }

    public  boolean isLearnerRegistered(Learner newlearner) {
        return listOfLearners.stream().anyMatch(learner ->
                Objects.equals(learner.getFirstName(), newlearner.getFirstName()) &&
                        Objects.equals(learner.getLastName(), newlearner.getLastName()));
    }
    public void printRegisteredLearners() {
        System.out.println("Registered Learners:");
        listOfLearners.forEach(learner -> System.out.println("User ID: " + learner.getUserId() +
                ", Name: " + learner.getFirstName() + " " + learner.getLastName() +
                ", Gender: " + learner.getGender() +
                ", Age: " + learner.getAge() +
                ", Emergency Contact Number: " + learner.getEmergencyContact()));
    }

    // Other methods as needed
}