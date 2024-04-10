package swimlessonapp.controllers;

import swimlessonapp.Config;

import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.AuthenticationView;


public class LearnerController {

    private static LearnerController instance;

    public static LearnerController getInstance() {
        if (instance == null) {
            instance = new LearnerController();
        }
        return instance;
    }

    LearnerRepository storedLearners = LearnerRepository.getInstance();
    Config config = new Config();

    public boolean checkAge(Learner newLearner) {
        return (newLearner.getAge() >= 4 && newLearner.getAge() <= 11);
    }

    public boolean checkEmergencyContact(Learner newLearner) {
        return !newLearner.getEmergencyContact().matches("\\d{10}") || !newLearner.getEmergencyContact().matches("[0-9]+");
    }

    public boolean checkGradeLevel(Learner newLearner){
        return (newLearner.getCurrentGradeLevel() >= 1 && newLearner.getCurrentGradeLevel() <= 5);
    }

    public void registerNewLearner(Learner newLearner) {
        if (!checkAge(newLearner)) {
            config.stringOutput("Age must be between 4 and 11 years old.");
            System.exit(0);
            return;
        }
        if (checkEmergencyContact(newLearner)) {
            config.stringOutput("Invalid emergency contact number format. It must be a 10-digit number.");
            System.exit(0);
            return;
        }

        if(!checkGradeLevel(newLearner)){
            config.stringOutput("Check Grade Level.");
            System.exit(0);
            return;
        }
        // Check if the learner already exists
        if (storedLearners.isLearnerRegistered(newLearner)) {
            config.stringOutput("User Exists");
        } else {
            storedLearners.addLearner(newLearner);
        }
    }

    public Learner existingLearner(String firstname, String lastname) {
        Learner isExistingLearner = storedLearners.getLearnerByName(firstname, lastname);
        if (isExistingLearner == null) {
            config.stringOutput("User doesn't exist");
            return null;
        }
        return isExistingLearner;
    }
}
