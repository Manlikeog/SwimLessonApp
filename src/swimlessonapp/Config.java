package swimlessonapp;

import java.util.Scanner;

public class Config {

    Scanner scanner = new Scanner(System.in);

    public int intInput(String displayText) {
        System.out.println(displayText);
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            return Integer.parseInt(input);
        } else {
            System.out.println("Invalid Entry!! Try Again");
            return intInput(displayText);
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
