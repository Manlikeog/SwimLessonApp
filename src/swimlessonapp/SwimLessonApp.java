package swimlessonapp;

import swimlessonapp.controllers.TimeTableController;

import static swimlessonapp.Config.*;

public class SwimLessonApp {
    private static final Menu menu = new Menu();
    private static final TimeTableController timeTable = new TimeTableController();

    public static void main(String[] args) {
        timeTable.generateWeekTimetable();
        runLessonApp();
        stringOutput("Exiting Swim Lesson App...");
    }

    public static void runLessonApp() {
        boolean continueApp;
        do {
            displayMainMenu();
            int choice = getUserChoice();
            switch (choice) {
                case MenuOptions.BOOK_LESSON:
                    menu.bookLesson();
                    break;
                case MenuOptions.CANCEL_LESSON:
                    menu.cancelOrChangeBooking();
                    break;
                case MenuOptions.EDIT_LESSON:
                    menu.editBooking();
                    break;
                case MenuOptions.ATTEND_LESSON:
                    menu.attendLesson();
                    break;
                case MenuOptions.MONTHLY_LEARNER_REPORT:
                    menu.learnerReport();
                    break;
                case MenuOptions.MONTHLY_COACH_REPORT:
                    menu.coachReport();
                    break;
                case MenuOptions.REGISTER_LEARNER:
                    menu.registerUser();
                    break;
                case MenuOptions.VIEW_TIMETABLE:
                    menu.viewTimeTable();
                    break;
                case MenuOptions.EXIT:
                    stringOutput("Exiting Swim Lesson App...");
                    System.exit(0);
                    break;
                default:
                    printResult(false,"Invalid choice. Please try again.");
                    break;
            }
            continueApp = askToReturnToMainMenu();
        } while (continueApp);
    }

    private static void displayMainMenu() {
        stringOutput("""
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
                8. View Full TimeTable
                9. Exit
                """);
    }

    private static int getUserChoice() {
        // Implement input validation
        return intInput("Enter your choice"); // Placeholder, replace with actual user input
    }

    private static boolean askToReturnToMainMenu() {
        char returnToMainMenu;
        do {
            returnToMainMenu = charInput("\nWould you like to return to the main menu? (Y/N)");
            if (returnToMainMenu != 'Y' && returnToMainMenu != 'N') {
                printResult(false,"Invalid choice. Please try again.");
            }
        } while (returnToMainMenu != 'Y' && returnToMainMenu != 'N');
        return returnToMainMenu == 'Y';
    }

    private static class MenuOptions {
        static final int BOOK_LESSON = 1;
        static final int CANCEL_LESSON = 2;
        static final int EDIT_LESSON = 3;
        static final int ATTEND_LESSON = 4;
        static final int MONTHLY_LEARNER_REPORT = 5;
        static final int MONTHLY_COACH_REPORT = 6;
        static final int REGISTER_LEARNER = 7;
        static final int VIEW_TIMETABLE = 8;
        static final int EXIT = 9;
    }
}
