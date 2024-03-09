package swimlessonapp;

import swimlessonapp.controllers.LearnerController;
import swimlessonapp.controllers.LessonController;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.repository.LessonRepository;

import java.util.Scanner;

/**
 * @author OG
 */

public class SwimLessonApp {
    static Scanner scanner = new Scanner(System.in);
    static Config config = new Config();

    static Learner learner = new Learner();
    static LearnerRepository storedLearners = new LearnerRepository();
    static CoachRepository storedCoach = new CoachRepository();
    static  LessonRepository storedLessons = new LessonRepository(storedCoach, storedLearners);



    static LearnerController manageLearner = new LearnerController(learner, storedLearners);
    static LessonController manageLesson = new LessonController(storedLessons);
    public static void main(String[] args) {
        System.out.println("Welcome to Hatfield Junior Swimming School");
        existingUser();
    }


    public static void existingUser() {
        String userType = config.stringInput("Are you an existing user? (Y/N)");

        char choice = Character.toUpperCase(userType.charAt(0))  ;
        switch (choice) {
            case 'Y':

                learner = manageLearner.login();
                menu();
                break;
            case 'N':
                manageLearner.registerNewLearner();
                break;
            default:
                config.stringOutput("Invalid choice. Please try again.");

        }
    }

    //Method to select menu
    public static void menu() {


        boolean bookLesson = true;

        do {
            config.stringOutput("Select an option:");
            config.stringOutput("1. Book a swimming lesson");
            config.stringOutput("2. Change/Cancel a booking");
            config.stringOutput("3. Attend a swimming lesson");
            config.stringOutput("4. Monthly learner report");
            config.stringOutput("5. Monthly coach report");
            config.stringOutput("6. Exit");

            //Select option
            int choice = config.intInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    config.stringOutput("Book swimming lesson");
                    manageLesson.viewAvailableLessons();
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
