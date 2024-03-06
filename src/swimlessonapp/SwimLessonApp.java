package swimlessonapp;

import swimlessonapp.controllers.LearnerController;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.LearnerRepository;

import java.util.Scanner;

/**
 * @author OG
 */

public class SwimLessonApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Hatfield Junior Swimming School");
        menu();
    }

    //Method to select menu
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        Learner newlearner = new Learner();
        LearnerRepository storedLearners = new LearnerRepository();
        LearnerController registerLearner = new LearnerController(newlearner, storedLearners);
        Config config = new Config();
        boolean bookLesson = true;

        do {
            config.stringOutput("Select an option:");
            config.stringOutput("1. Book a swimming lesson");
            config.stringOutput("2. Change/Cancel a booking");
            config.stringOutput("3. Attend a swimming lesson");
            config.stringOutput("4. Monthly learner report");
            config.stringOutput("5. Monthly coach report");
            config.stringOutput("6. Register a new learner");
            config.stringOutput("7. Exit");

            //Select option
            int choice = config.intInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    config.stringOutput("Book swimming lesson");
                    break;
                case 2:
                    config.stringOutput("Cancel swimming lesson");
                    break;
                case 3:
                    config.stringOutput("Attend swimming lesson");
                    break;
                case 4:
                    config.stringOutput("Generate monthly learner");
                    break;
                case 5:
                    config.stringOutput("Generate monthly coach");
                    break;
                case 6:

                    registerLearner.registerNewLearner();

                    break;
                case 7:
                    config.stringOutput("Exiting...");
                    System.exit(0);
                    break;
                default:
                    config.stringOutput("Invalid choice. Please try again.");
            }

            //Recursion for selecting another option
            char bookAgain = '0';
            while (bookAgain != 'Y' && bookAgain != 'N') {
                System.out.println("\nWould you like to perform another action? (Y / N)");
                bookAgain = scanner.next().charAt(0);
                bookAgain = Character.toUpperCase(bookAgain);

                switch (bookAgain) {
                    case 'N':
                        System.out.println("Goodbye!");
                        bookLesson = false;
                        break;
                    case 'Y':
                        continue;
                    default:
                        config.stringOutput("Invalid choice. Please try again.");

                }
            }
        } while (bookLesson);
    }

}
