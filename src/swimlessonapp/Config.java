package swimlessonapp;

import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;

import java.util.Random;
import java.util.Scanner;

public class Config {

    Scanner scanner = new Scanner(System.in);

    public  int intInput(String displayText) {
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

    public String stringInput(String displayText) {
        System.out.println(displayText);
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            return input;
        } else {
            System.out.println("Invalid Entry!! Try Again");
            return stringInput(displayText);
        }
    }

    public void stringOutput(String displayText) {
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
