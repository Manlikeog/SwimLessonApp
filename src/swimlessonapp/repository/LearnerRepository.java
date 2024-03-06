package swimlessonapp.repository;

import swimlessonapp.model.Learner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LearnerRepository {
    private static final List<Learner> listOfLearners = new ArrayList<>();

    // Add learners to the list
    static {
        listOfLearners.add(new Learner(1, "John", "Doe", "Male", 8, "1234567890", 1));
        listOfLearners.add(new Learner(1, "John", "Doe", "Male", 8, "1234567890", 1));
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
    public static Learner getLearnerById(int userId) {
        for (Learner learner : listOfLearners) {
            if (learner.getUserId() == userId) {
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


    // Other methods as needed
}