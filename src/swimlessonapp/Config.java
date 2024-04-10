package swimlessonapp;

import swimlessonapp.controllers.LearnerController;
import swimlessonapp.model.Learner;

import java.util.Random;
import java.util.Scanner;

public class Config {

    static Scanner scanner = new Scanner(System.in);
    static LearnerController manageLearner = LearnerController.getInstance();


    public static int intInput(String displayText) {
        System.out.println(displayText);
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            try {
                return Integer.parseInt(input); // Attempt to parse the input as an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid Entry!! Please enter a valid integer.");
                return intInput(displayText); // Prompt again for valid input
            }
        } else {
            System.out.println("Invalid Entry!! Try Again");
            return intInput(displayText); // Prompt again for valid input
        }
    }

    public static String stringInput(String displayText) {
        System.out.println(displayText);
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            return input;
        } else {
            System.out.println("Invalid Entry!! Try Again");
            return stringInput(displayText);
        }
    }

    public char charInput(String displayText) {
        System.out.println(displayText);
        String input = scanner.nextLine();
        String charUpper = input.toUpperCase();
        if (!charUpper.isEmpty()) {
            return charUpper.charAt(0);
        } else {
            System.out.println("Invalid Entry!! Try Again");
            return charInput(displayText);
        }
    }

    public static Learner getUser() {
        Learner learner;
        boolean userExists = false;
        do {
            String firstName = stringInput("Enter first name: ");
            String lastName = stringInput("Enter last name: ");
            learner = manageLearner.existingLearner(firstName, lastName);

            if (learner != null) {
                userExists = true;
            } else {
                System.out.println("User not found. Please try again.");
            }
        } while (!userExists);
        return learner;
    }

    public static void stringOutput(String displayText) {
        System.out.println("\n" + displayText);
    }

    public static int generateUserId() {
        Random random = new Random();
        int newUserId;
        boolean exists;
        do {
            exists = false;
            newUserId = random.nextInt(1000); // Generate a random integer as user ID

            // Check if the generated ID already exists
//            for (Learner learner : LearnerRepository.getAllLearners()) {
//                if (learner.getUserId() == newUserId) {
//                    exists = true;
//                    break;
//                }
//            }
        } while (exists);
        return newUserId;
    }
}
