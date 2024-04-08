package swimlessonapp;

import swimlessonapp.controllers.LearnerController;
import swimlessonapp.controllers.LessonController;
import swimlessonapp.controllers.TimeTableController;
import swimlessonapp.model.Learner;
import swimlessonapp.view.AuthenticationView;
import swimlessonapp.view.MenuView;
import swimlessonapp.view.TimeTableView;

/**
 * @author OG
 */

public class SwimLessonApp {
    private static final Config config = new Config();
    private static final MenuView menu = new MenuView();

    private static final AuthenticationView authentication = new AuthenticationView();
    static final TimeTableController timeTable = new TimeTableController();

    public static void main(String[] args) {
        menu();
    }

    //Method to select menu
    public static void menu() {
        timeTable.generateWeekTimetable();
        do {
            int choice = config.intInput("""
                    Welcome to Hatfield Junior Swimming School
                    +-----------------------------------------------------+
                    1. Login\s
                    2. SignUp\s
                    3. Exit\s
                                        
                    Please input number to proceed:""");
            switch (choice) {
                case 1:
                    authentication.learnerDetailsInput(false);
                    break;
                case 2:
                    authentication.learnerDetailsInput(true);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, Please Try Again");
                    continue;
            }
            lessonApp();
        } while (true);
    }

    public static void lessonApp() {
        boolean bookLesson = true;
        do {
            System.out.println("""
                    Select an option:
                    1. Book a swimming lesson
                    2. Change/Cancel a booking
                    3. Attend a swimming lesson
                    4. Monthly learner report
                    5. Monthly coach report
                    6. LogOut
                    """);

            //Select option
            int choice = config.intInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    menu.bookLesson();
                    break;
                case 2:
                    config.stringOutput("Cancel swimming lesson");
                    break;
                case 3:
                    menu.attendLesson();
                    break;
                case 4:
                    config.stringOutput("Generate monthly learner");
                    break;
                case 5:
                    config.stringOutput("Generate monthly coach");
                    break;
                case 6:
                    config.stringOutput("Logging Out...");
                    bookLesson = false;
                    break;
                default:
                    config.stringOutput("Invalid choice. Please try again.");
                    continue;
            }

            //Recursion for selecting another option
            if (bookLesson) {
                char bookAgain = '0';
                while (bookAgain != 'Y' && bookAgain != 'N') {
                    bookAgain = config.charInput("\nWould you like to perform another action? (Y / N)");
                    switch (bookAgain) {
                        case 'N':
                            config.stringOutput("Logging Out.....!");
                            bookLesson = false;
                            break;
                        case 'Y':
                            continue;
                        default:
                            config.stringOutput("Invalid choice. Please try again.");
                    }
                }
            }
        } while (bookLesson);
    }

}
