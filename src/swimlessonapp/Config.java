package swimlessonapp;

import java.util.Scanner;

public class Config {

    static Scanner scanner = new Scanner(System.in);
    public static int intInput(String displayText) {
        System.out.println(displayText + " (E:EXIT):");
        String input = scanner.nextLine().trim(); // Trim to remove leading/trailing whitespaces
        if (input.equalsIgnoreCase("E")) {
            System.exit(0); // Exiting the program
        }
        try {
            return Integer.parseInt(input); // Attempt to parse the input as an integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid Entry!! Please enter a valid integer.");
            return intInput(displayText); // Prompt again for valid input
        }
    }

    public static String stringInput(String displayText) {
        System.out.println(displayText + " (E:EXIT):");
        String input = scanner.nextLine().trim(); // Trim to remove leading/trailing whitespaces
        if (input.equalsIgnoreCase("E")) {
            System.exit(0); // Exiting the program
        }
        if (input.isEmpty()) {
            System.out.println("Invalid Entry!! Try Again");
            return stringInput(displayText);
        }
        return input;
    }

    public static char charInput(String displayText) {
        System.out.println(displayText);
        String input = scanner.nextLine().trim().toUpperCase(); // Trim and convert to upper case
        if (input.equalsIgnoreCase("E")) {
            System.exit(0); // Exiting the program
        }
        if (input.isEmpty()) {
            System.out.println("Invalid Entry!! Try Again");
            return charInput(displayText);
        }
        return input.charAt(0);
    }

    public static void stringOutput(String displayText) {
        System.out.println("\n" + displayText);
    }

//    public static int generateUserId() {
//        Random random = new Random();
//        int newUserId;
//        boolean exists;
//        do {
//            exists = false;
//            newUserId = random.nextInt(1000); // Generate a random integer as user ID
//
//            // Check if the generated ID already exists
////            for (Learner learner : LearnerRepository.getAllLearners()) {
////                if (learner.getUserId() == newUserId) {
////                    exists = true;
////                    break;
////                }
////            }
//        } while (exists);
//        return newUserId;
//    }
    public static String promptAndGetLessonId() {
        return """ 
                Select Lesson to book above!!
                Input Lesson ID""";
    }

    public static int promptAndGetBookingId() {
        return  intInput("""
                To select lesson, Input ID""");
    }
}
