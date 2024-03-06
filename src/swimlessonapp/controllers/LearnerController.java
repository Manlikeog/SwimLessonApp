package swimlessonapp.controllers;

import swimlessonapp.Config;

import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.LearnerView;


public class LearnerController {
   private Learner newLearner;
   private final LearnerRepository storedLearners;
    public LearnerController(Learner newLearner, LearnerRepository storedLearners) {
        this.newLearner = newLearner;
        this.storedLearners = storedLearners;
    }
    static Config config = new Config();

    public void registerNewLearner() {
        newLearner = LearnerView.learnerDetailsInput();

        if (!checkAge()) {
            config.stringOutput("Age must be between 4 and 11 years old.");
            return;
        }
        if (checkEmergencyContact()) {
            config.stringOutput("Invalid emergency contact number format. It must be a 10-digit number.");
            do{
              String  emergencyContact = config.stringInput("Invalid Format! Re-Enter Emergency Number: ");
                newLearner.setEmergencyContact(emergencyContact);
            } while (checkEmergencyContact());
        }

        // Check if the learner already exists
        if (storedLearners.isLearnerRegistered(newLearner)) {
            config.stringOutput("User already exists!");
        } else {
            storedLearners.addLearner(newLearner);
            config.stringOutput("Hurray!!! " + newLearner.getFirstName() + " " + newLearner.getLastName() + " you have been registered with Hatfield junior swimming lesson");
            LearnerView.printRegisteredLearners(storedLearners);
        }
    }

    public boolean checkAge() {
        return (newLearner.getAge() >= 4 && newLearner.getAge() <= 11);
    }

    public boolean checkEmergencyContact() {
        return !newLearner.getEmergencyContact().matches("\\d{10}") || !newLearner.getEmergencyContact().matches("[0-9]+");
    }
}
