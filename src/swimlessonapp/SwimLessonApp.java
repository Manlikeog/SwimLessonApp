package swimlessonapp;

import swimlessonapp.view.LearnerView;

/**
 * @author OG
 */

public class SwimLessonApp {
   private static final Config config = new Config();
    public static void main(String[] args) {
        System.out.println("Welcome to Hatfield Junior Swimming School");
        menu();
    }

    //Method to select menu
    public static void menu() {
        existingUser();
        boolean bookLesson = true;
        do {
            System.out.println("""
                    Select an option:
                    1. Book a swimming lesson
                    2. Change/Cancel a booking
                    3. Attend a swimming lesson
                    4. Monthly learner report
                    5. Monthly coach report
                    6. Exit
                    """);

            //Select option
            int choice = config.intInput("Enter your choice: ");
            switch (choice) {
                case 1: config.stringOutput("Book swimming lesson");
                    break;
                case 2: config.stringOutput("Cancel swimming lesson");
                    break;
                case 3: config.stringOutput("Attend swimming lesson");
                    break;
                case 4: config.stringOutput("Generate monthly learner");
                    break;
                case 5: config.stringOutput("Generate monthly coach");
                    break;
                case 6: config.stringOutput("Exiting...");
                    System.exit(0);
                    break;
                default: config.stringOutput("Invalid choice. Please try again.");
            }

            //Recursion for selecting another option
            char bookAgain = '0';
            while (bookAgain != 'Y' && bookAgain != 'N') {
                bookAgain = config.charInput("\nWould you like to perform another action? (Y / N)");
                switch (bookAgain) {
                    case 'N':
                        config.stringOutput("Goodbye!");
                        bookLesson = false;
                        break;
                    case 'Y':
                        continue;
                    default: config.stringOutput("Invalid choice. Please try again.");
                }
            }
        } while (bookLesson);
    }

    public static void existingUser(){
        int choice = config.intInput("""
                    +-----------------------------------------------------+
                    1. Login\s
                    2. SignUp\s
                    3. Exit\s
                    """);
        switch (choice) {
            case 1: LearnerView.learnerDetailsInput(false);
                    break;
                case 2: LearnerView.learnerDetailsInput(true);
                    break;
                case 3: System.exit(0);
                    break;
                default: System.out.println("Invalid choice");
            }
    }
}
