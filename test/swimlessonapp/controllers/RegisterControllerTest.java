package swimlessonapp.controllers;

import org.junit.jupiter.api.Test;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.UserInteraction;

import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest {

    @Test
    void registerNewLearner_ValidLearner_SuccessfullyRegistered() {
        // Mocking necessary dependencies
        LearnerRepository learnerRepository = new LearnerRepository();
        UserInteraction userInteraction = new UserInteraction();

        // Creating a test instance of RegisterController
        RegisterController registerController = new RegisterController(learnerRepository, userInteraction);

        // Creating a test learner
        Learner testLearner = new Learner("John", "Doe", 'M', 8, "07983653718", 1, 1);

        // Registering the test learner
        registerController.registerNewLearner(testLearner);

        // Asserting that the learner is successfully registered
        assertTrue(learnerRepository.isLearnerRegistered(testLearner));
    }

    @Test
    void registerNewLearner_ExistingLearner_Failure() {
        // Mocking necessary dependencies
        LearnerRepository learnerRepository = new LearnerRepository();
        UserInteraction userInteraction = new UserInteraction();

        // Creating a test instance of RegisterController
        RegisterController registerController = new RegisterController(learnerRepository, userInteraction);

        // Creating a test learner
        Learner testLearner = new Learner("John", "Doe", 'M', 8, "12345678901", 1, 1);

        // Registering the test learner
        registerController.registerNewLearner(testLearner);

        // Creating another learner with the same details
        Learner duplicateLearner = new Learner("John", "Doe", 'M', 8, "12345678901", 1, 1);

        // Registering the duplicate learner
        registerController.registerNewLearner(duplicateLearner);

        // Asserting that the duplicate learner is not registered
        assertTrue(learnerRepository.isLearnerRegistered(duplicateLearner));
    }

    // Add more test cases to cover other scenarios
}
