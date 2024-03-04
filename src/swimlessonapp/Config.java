package swimlessonapp;

import java.util.Scanner;

public class Config {

    Scanner scanner = new Scanner(System.in);

    public int intInput(String displayText) {
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
}
