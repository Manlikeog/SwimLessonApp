package swimlessonapp.view;

import swimlessonapp.Config;


import swimlessonapp.controllers.LearnerController;
import swimlessonapp.model.Learner;


public class LearnerView {

    static Config config = new Config();

    private static final LearnerController manageLearner = LearnerController.getInstance();

    public static void learnerDetailsInput(boolean registerUser) {
        Learner newLearner = new Learner();
        String firstName = config.stringInput("Enter first name: ").toUpperCase();
        newLearner.setFirstName(firstName);
        String lastName = config.stringInput("Enter last name: ").toUpperCase();
        newLearner.setLastName(lastName);

        //Ask more details to register new user is new
        if (registerUser) {
            String gender = config.stringInput("Enter gender: ");
            newLearner.setGender(gender);
            int age = config.intInput("Enter age: ");
            newLearner.setAge(age);
            String emergencyContact = config.stringInput("Enter emergency contact number: ");
            newLearner.setEmergencyContact(emergencyContact);
            manageLearner.registerNewLearner(newLearner);
        } else {
            manageLearner.existingLearner(newLearner);
        }

    }

}
