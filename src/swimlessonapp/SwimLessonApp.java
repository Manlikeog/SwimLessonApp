package swimlessonapp;

import swimlessonapp.controllers.TimeTableController;
import swimlessonapp.view.MenuView;


import static swimlessonapp.Config.intInput;
import static swimlessonapp.Config.stringOutput;

/**
 * @author OG
 */

public class SwimLessonApp {
    private static final Config config = new Config();
    private static final MenuView menu = new MenuView();
   private static TimeTableController timeTable = new TimeTableController();

    public static void main(String[] args) {
        timeTable.generateWeekTimetable();
        menu();
    }

    //Method to select menu
    public static void menu() {
        lessonApp();
    }

    public static void lessonApp() {
        boolean bookLesson = true;
        do {
            System.out.println("""
                    Welcome to Hatfield Junior Swimming School
                    +-----------------------------------------------------+
                    Select an option:
                    1. Book a swimming lesson
                    2. Cancel booked lesson
                    3. Edit booked Lesson
                    4. Attend a swimming lesson
                    5. Monthly learner report
                    6. Monthly coach report
                    7. Register Learner
                    7. Exit
                    """);

            //Select option
            int choice = intInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    menu.bookLesson();
                    break;
                case 2:
                    menu.cancelOrChangeBooking();
                    break;
                case 3:
                    menu.editBooking();
                    break;
                case 4:
                    menu.attendLesson();
                    break;
                case 5:
                   stringOutput("Generate monthly learner");
                    break;
                case 6:
                    stringOutput("Generate monthly coach");
                    break;
                case 7:
                    stringOutput("Exit");
                    bookLesson = false;
                    break;
                default:
                    stringOutput("Invalid choice. Please try again.");
                    continue;
            }

            //Recursion for selecting another option
            if (bookLesson) {
                char bookAgain = '0';
                while (bookAgain != 'Y' && bookAgain != 'N') {
                    bookAgain = config.charInput("\nWould you like to perform another action? (Y / N)");
                    switch (bookAgain) {
                        case 'N':
                            stringOutput("Exit.....!");
                            bookLesson = false;
                            break;
                        case 'Y':
                            continue;
                        default:
                            stringOutput("Invalid choice. Please try again.");
                    }
                }
            }
        } while (bookLesson);
    }

}
