package swimlessonapp.view;

import swimlessonapp.Config;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.controllers.TimeTableController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TimeTableView {
    Config config = new Config();
    TimeTableController timeTable = new TimeTableController();

    public void viewTimeTable() {
        config.stringOutput("""
                Select an option to view the timetable:
                1. View full week timetable
                2. View timetable for a specific day
                3. View timetable for a specific grade level
                4. View timetable for a specific coach
                """);

        int choice = config.intInput("Enter your choice: ");
        switch (choice) {
            case 1:
                printLessonsForWeek();
                break;
            case 2:
                String day = config.stringInput("Enter the day (e.g., Monday): ");
                printLessonsForDay(day);
                break;
            case 3:
                int gradeLevel = config.intInput("Enter the grade level: ");
                printLessonsForGradeLevel(gradeLevel);
                break;
            case 4:
                String coachName = config.stringInput("Enter the coach's name: ");
                printLessonsForCoach(coachName);
                break;
            default:
                config.stringOutput("Invalid choice!");
        }
    }

    public void printLessonsForWeek() {
        printLessons("Week", timeTable.viewFullTimeTable());
    }

    public void printLessonsForDay(String day) {
        printLessons("Day: " + day, timeTable.viewTimeTableByDay(day));
    }

    public void printLessonsForGradeLevel(int gradeLevel) {
        printLessons("Grade " + gradeLevel, timeTable.viewTimeTableByGradeLevel(gradeLevel));
    }

    public void printLessonsForCoach(String coachName) {
        printLessons("Coach: " + coachName, timeTable.viewTimeTableByCoach(coachName));
    }

    private void printLessons(String title, List<Lesson> lessons) {
        config.stringOutput("Available Lessons for " + title + ":");

        lessons.sort(Comparator.comparing(lesson -> Arrays.asList("Monday", "Wednesday", "Friday", "Saturday").indexOf(lesson.getDay())));

        System.out.printf("%-10s%-10s%-20s%-20s%-15s%-50s%-15s%s%n", "LessonID",
                "Day", "Time", "Coach", "Participants", "Learners", "Class Size", "Grade Level");

        for (Lesson lesson : lessons) {
            String learners = getLearnersString(lesson.getLearners());
            System.out.printf("%-10s%-10s%-20s%-20s%-15s%-50s%-15s%s%n", lesson.getId(),
                    lesson.getDay(), lesson.getTime(), lesson.getCoach().name(),
                    lesson.getLearners().size(), learners,
                    lesson.getMaxLearners(), lesson.getGradeLevel());
        }
    }

    private static String getLearnersString(List<Learner> learners) {
        StringBuilder learnerNames = new StringBuilder();
        for (Learner learner : learners) {
            learnerNames.append(learner.getFirstName()).append(" ").append(learner.getLastName()).append(", ");
        }
        if (learnerNames.length() > 2) {
            learnerNames.delete(learnerNames.length() - 2, learnerNames.length()); // Remove the extra ", " at the end
        }
        return learnerNames.toString();
    }
}
