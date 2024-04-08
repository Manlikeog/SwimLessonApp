package swimlessonapp.controllers;

import swimlessonapp.Config;

import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.AuthenticationView;


public class LearnerController {
    private Learner learner = new Learner();
    private static LearnerController instance;

    public static LearnerController getInstance() {
        if (instance == null) {
            instance = new LearnerController();
        }
        return instance;
    }

    LearnerRepository storedLearners = LearnerRepository.getInstance();
    Config config = new Config();
    private static final AuthenticationView authentication = new AuthenticationView();

    public boolean checkAge(Learner newLearner) {
        return (newLearner.getAge() >= 4 && newLearner.getAge() <= 11);
    }

    public boolean checkEmergencyContact(Learner newLearner) {
        return !newLearner.getEmergencyContact().matches("\\d{10}") || !newLearner.getEmergencyContact().matches("[0-9]+");
    }

    public void registerNewLearner(Learner newLearner) {
        if (!checkAge(newLearner)) {
            config.stringOutput("Age must be between 4 and 11 years old.");
        }
        if (checkEmergencyContact(newLearner)) {
            config.stringOutput("Invalid emergency contact number format. It must be a 10-digit number.");
        }
        // Check if the learner already exists
        if (storedLearners.isLearnerRegistered(newLearner)) {
            config.stringOutput("User Exists");
        } else {
            storedLearners.addLearner(newLearner);
            setLearner(newLearner);
            storedLearners.printRegisteredLearners();
        }
    }

    public void existingLearner(Learner learner) {
        Learner isExistingLearner = storedLearners.getLearnerByName(learner);
        if (isExistingLearner == null) {
            config.stringOutput("User doesn't exist");
            int option = config.intInput("1. Try Again, 2. Register Learner, 3. Exit");
            switch (option) {
                case 1:
                    authentication.learnerDetailsInput(false);
                    break;
                case 2:
                    authentication.learnerDetailsInput(true);
                    break;
                case 3:
                    System.exit(0);
                default:
                    config.stringOutput("Invalid choice. Please try again.");
            }
        } else {
            setLearner(isExistingLearner);
        }
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }
}
