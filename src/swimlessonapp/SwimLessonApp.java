package swimlessonapp;

import java.util.Scanner;

/**
 *
 * @author OG
 */

public class SwimLessonApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Hatfield Junior Swimming School");
        learnerDetailsInput();
    }

    //Method to allow user input details
    private static void learnerDetailsInput()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter gender: ");
        String gender = input.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.print("Enter emergency contact number: ");
        String emergencyContactNumber = input.nextLine();

        // Create learner object and register
        Learner newlearner = new Learner(firstName, lastName, gender, age, emergencyContactNumber);
        newlearner.registerNewLearner();
    }


}
