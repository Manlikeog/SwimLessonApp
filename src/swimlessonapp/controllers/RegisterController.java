package swimlessonapp.controllers;

import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

import static swimlessonapp.Config.*;


public class RegisterController extends ActionController {

    private final LearnerRepository storedLearners;
    private final TimeTableView timeTableView;

    public RegisterController(LearnerRepository learnerRepository, TimeTableView timeTableView) {
        super(null, timeTableView, null, learnerRepository, null, null);
        this.storedLearners = learnerRepository;
        this.timeTableView = timeTableView;
    }

    @Override
    public void performAction() {
        Learner newLearner = timeTableView.learnerDetailsInput();
        registerNewLearner(newLearner);
        redoAction("Register another learner");
    }

    private boolean checkAge(Learner newLearner) {
        return (newLearner.getAge() >= 4 && newLearner.getAge() <= 11);
    }

    public boolean checkEmergencyContact(Learner newLearner) {
        return !newLearner.getEmergencyContact().matches("\\d{11}") || !newLearner.getEmergencyContact().matches("[0-10]+");
    }

    private boolean checkGradeLevel(Learner newLearner) {
        return (newLearner.getCurrentGradeLevel() >= 1 && newLearner.getCurrentGradeLevel() <= 5);
    }


    public void registerNewLearner(Learner newLearner) {
        if (!checkAge(newLearner)) {
            printResult(false,"Age must be between 4 and 11 years old.");
            return;
        }
        if (checkEmergencyContact(newLearner)) {
            printResult(false,"Invalid emergency contact number format. It must be a 11-digit number.");
            return;
        }

        if (!checkGradeLevel(newLearner)) {
            printResult(false,"Lesson must be between Grade Level. Grade level 1-5");
            return;
        }
        // Check if the learner already exists
        if (storedLearners.isLearnerRegistered(newLearner)) {
            printResult(false,"User Exists");
        } else {
            storedLearners.addLearner(newLearner);
            printResult(true,"Hi " + newLearner.getFirstName() + " " + newLearner.getLastName() + " you have been registered successfully");
        }
    }
}
