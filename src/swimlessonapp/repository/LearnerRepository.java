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
        listOfLearners.add(new Learner(1, "JOHN", "DOE", "Male", 8, "1234567890", 1));
        listOfLearners.add(new Learner(2, "ALICE", "SMITH", "Female", 7, "2345678901", 2));
        listOfLearners.add(new Learner(3, "BOB", "JOHNSON", "Male", 9, "3456789012", 3));
        listOfLearners.add(new Learner(4, "EMILY", "BROWN", "Female", 6, "4567890123", 4));
        listOfLearners.add(new Learner(5, "DAVID", "WILSON", "Male", 8, "5678901234", 5));
        listOfLearners.add(new Learner(6, "SOPHIA", "MARTINEZ", "Female", 7, "6789012345", 6));
        listOfLearners.add(new Learner(7, "MICHAEL", "ANDERSON", "Male", 9, "7890123456", 7));
        listOfLearners.add(new Learner(8, "OLIVIA", "TAYLOR", "Female", 6, "8901234567", 8));
        listOfLearners.add(new Learner(9, "EMMA", "THOMAS", "Female", 8, "9012345678", 9));
        listOfLearners.add(new Learner(10, "JAMES", "JACKSON", "Male", 7, "0123456789", 10));
        listOfLearners.add(new Learner(11, "DANIEL", "WHITE", "Male", 9, "1357902468", 11));
        listOfLearners.add(new Learner(12, "SOPHIE", "HARRIS", "Female", 7, "2468013579", 12));
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
    public Learner getLearnerByName(Learner newLearner) {
        for (Learner learner : listOfLearners) {
            if (Objects.equals(learner.getFirstName(), newLearner.getFirstName()) &&
                    Objects.equals(learner.getLastName(), newLearner.getLastName())) {
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