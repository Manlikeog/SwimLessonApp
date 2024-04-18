package swimlessonapp.controllers;

import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.UserInteraction;

import static swimlessonapp.Config.*;


public class RegisterController extends BaseController {
    public RegisterController(LearnerRepository learnerRepository, UserInteraction userInteraction) {
        super(null, learnerRepository, null, null, userInteraction, null);
    }

    @Override
    public void performAction() {
        Learner newLearner = userInteraction.learnerDetailsInput();
        registerNewLearner(newLearner);
        redoAction("Register another learner");
    }
    void registerNewLearner(Learner newLearner) {
        if (validateLearnerDetails(newLearner)) {
            if (learnerRepository.isLearnerRegistered(newLearner)) {
                printResult(false, "User already exists.");
            } else {
                learnerRepository.addLearner(newLearner);
                printResult(true, "User registered successfully.");
            }
        }
    }

    private boolean validateLearnerDetails(Learner newLearner) {
        if (!checkAge(newLearner)) {
            printResult(false, "Age must be between 4 and 11 years old.");
            return false;
        }
        if (checkEmergencyContact(newLearner)) {
            printResult(false, "Invalid emergency contact number format. It must be an 11-digit number.");
            return false;
        }
        if (!checkGradeLevel(newLearner)) {
            printResult(false, "Grade level must be between 1 and 5.");
            return false;
        }
        return true;
    }

    private boolean checkAge(Learner newLearner) {
        return (newLearner.getAge() >= 4 && newLearner.getAge() <= 11);
    }

    private boolean checkEmergencyContact(Learner newLearner) {
        return !newLearner.getEmergencyContact().matches("\\d{11}");
    }

    private boolean checkGradeLevel(Learner newLearner) {
        return (newLearner.getCurrentGradeLevel() >= 1 && newLearner.getCurrentGradeLevel() <= 5);
    }
}
