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
            printResult(false,"Invalid Entry!! Please enter a valid integer.");
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
            printResult(false,"Invalid Entry!! Try Again");
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
            printResult(false,"Invalid Entry!! Try Again");
            return charInput(displayText);
        }
        return input.charAt(0);
    }

    public static void stringOutput(String displayText) {
        System.out.println("\n" + displayText);
    }

    public static String promptAndGetLessonId() {
        return """ 
                Select Lesson to book above!!
                Input Lesson ID""";
    }
    public static void printResult(boolean isGreen, String text) {
        if (isGreen) {
            System.out.println("\u001B[32m" + text + "\u001B[0m"); // Print text in green
        } else {
            System.out.println("\u001B[31m" + text + "\u001B[0m"); // Print text in red
        }
    }
    public static int promptAndGetBookingId() {
        return  intInput("""
                To select lesson, Input ID""");
    }
}
