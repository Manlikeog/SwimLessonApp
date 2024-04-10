package swimlessonapp.view;
import swimlessonapp.controllers.LearnerController;
import swimlessonapp.model.Learner;

import static swimlessonapp.Config.*;


public class AuthenticationView {



    LearnerController manageLearner = LearnerController.getInstance();

    public void learnerDetailsInput(boolean registerUser) {

        String firstName = stringInput("Enter first name: ").toUpperCase();
        String lastName = stringInput("Enter last name: ").toUpperCase();
        String gender = stringInput("Enter gender: ");
        int age = intInput("Enter age: ");
        String emergencyContact = stringInput("Enter emergency contact number: ");
        int gradeLevel = intInput("Enter GradeLevel: ");
        Learner newLearner = new Learner(firstName, lastName, gender, age, emergencyContact, gradeLevel);
        manageLearner.registerNewLearner(newLearner);

    }


}
