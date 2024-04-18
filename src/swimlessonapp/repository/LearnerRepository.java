package swimlessonapp.repository;

import swimlessonapp.model.Learner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static swimlessonapp.Config.stringOutput;

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
        listOfLearners.add(new Learner("JOHN", "DOE", 'M', 8, "1234567890", 1, 1));
        listOfLearners.add(new Learner("ALICE", "SMITH", 'F', 7, "2345678901", 2, 2));
        listOfLearners.add(new Learner("BOB", "JOHNSON", 'M', 9, "3456789012", 3, 3));
        listOfLearners.add(new Learner("EMILY", "BROWN", 'F', 6, "4567890123", 4, 4));
        listOfLearners.add(new Learner("DAVID", "WILSON", 'M', 8, "5678901234", 5, 5));
        listOfLearners.add(new Learner("SOPHIA", "MARTINEZ", 'F', 7, "6789012345", 1, 6));
        listOfLearners.add(new Learner("MICHAEL", "ANDERSON", 'M', 9, "7890123456", 2, 7));
        listOfLearners.add(new Learner("OLIVIA", "TAYLOR", 'F', 6, "8901234567", 3, 8));
        listOfLearners.add(new Learner("EMMA", "THOMAS", 'F', 8, "9012345678", 4, 9));
        listOfLearners.add(new Learner("JAMES", "JACKSON", 'M', 7, "0123456789", 5, 10));
        listOfLearners.add(new Learner("DANIEL", "WHITE", 'M', 9, "1357902468", 1, 11));
        listOfLearners.add(new Learner("SOPHIE", "HARRIS", 'F', 7, "2468013579", 2, 12));
        listOfLearners.add(new Learner("SARAH", "MILLER", 'F', 8, "5678901234", 2, 13));
        listOfLearners.add(new Learner("JACOB", "LEE", 'M', 7, "6789012345", 3, 14));
        listOfLearners.add(new Learner("GRACE", "TAYLOR", 'F', 9, "7890123456", 4, 15));
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
    public Learner getLearnerByName(int userId) {
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

    public Learner existingLearner(int userID) {
        Learner isExistingLearner = getLearnerByName(userID);
        if (isExistingLearner == null) {
            stringOutput("User doesn't exist");
            return null;
        }
        return isExistingLearner;
    }
    // Other methods as needed
}