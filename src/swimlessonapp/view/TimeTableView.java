package swimlessonapp.view;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static swimlessonapp.Config.*;

public class TimeTableView {

    public boolean viewTimeTableByCriteria(String prompt, Function<Lesson, ?> criteriaExtractor, List<Lesson> lessons) {
        String userInput = stringInput(prompt);
        List<Lesson> filteredLessons = filterLessons(lesson -> criteriaExtractor.apply(lesson).toString().equalsIgnoreCase(userInput), lessons);
        if (filteredLessons.isEmpty()) {
            stringOutput("There is no available lesson for " + userInput + " this week");
            return false;
        } else {
           viewLessons(prompt.substring(6), filteredLessons); // Displaying based on the provided criteria
        }
        return true;
    }

    private List<Lesson> filterLessons(Predicate<Lesson> predicate, List<Lesson> lessons) {
        return lessons.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    public void viewLessons(String option, List<Lesson> lessons) {
        printLessons(option, lessons);
    }

    public void printLessons(String title, List<Lesson> lessons) {
       stringOutput("Available Lessons for " + title + ":");

        lessons.sort(Comparator.comparing(lesson -> Arrays.asList("Monday", "Wednesday", "Friday", "Saturday").indexOf(lesson.getDay())));

        System.out.printf("%-10s%-10s%-20s%-20s%-15s%-70s%-15s%s%n", "LessonID",
                "Day", "Time", "Coach", "Participants", "Learners", "Class Size", "Grade Level");

        for (Lesson lesson : lessons) {
            String learners = getLearnersString(lesson.getLearners());
            System.out.printf("%-10s%-10s%-20s%-20s%-15s%-70s%-15s%s%n", lesson.getId(),
                    lesson.getDay(), lesson.getTime(), lesson.getCoach().getName(),
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


    public void displayBookings(List<Book> bookings) {
        stringOutput("Available Bookings:");
        System.out.printf("%-12s%-15s%-10s%-20s%-20s%-10s%-10s%-10s%-10s%n",
                "BookingID", "User", "Day", "Time", "Coach", "Month", "Grade", "Status",  "Rating");
        for (Book book : bookings) {
            System.out.printf("%-12s%-15s%-10s%-20s%-20s%-10s%-10s%-10s%-10s%n",
                    book.getId(),
                    book.getLearner().getFirstName() + " " + book.getLearner().getLastName(),
                    book.getLesson().getDay(),
                    book.getLesson().getTime(),
                    book.getLesson().getCoach().getName(),
                    book.getMonth(),
                    book.getLesson().getGradeLevel(),
                    book.getStatus(),
                    book.getRating());
        }
    }

    public boolean printTimeTable(List<Lesson> lessons) {

        stringOutput("""
                Select an option to view the timetable:
                1. View timetable for a specific day
                2. View timetable for a specific grade level
                3. View timetable for a specific coach
                """);

        int choice = intInput("Enter your choice");
        return switch (choice) {
            case 1 -> viewTimeTableByCriteria("Enter the day (e.g., Monday)", Lesson::getDay, lessons);
            case 2 -> viewTimeTableByCriteria("Enter the grade level", Lesson::getGradeLevel, lessons);
            case 3 ->
                    viewTimeTableByCriteria("Enter the coach's name", lesson -> lesson.getCoach().getName(), lessons);
            default -> {
                stringOutput("Invalid choice!");
                yield false;
            }
        };

    }

    public Learner learnerDetailsInput() {
        String firstName = stringInput("Enter first name").toUpperCase();
        String lastName = stringInput("Enter last name").toUpperCase();
        char gender = charInput("Enter gender (M/F/E(EXIT))");
        int age = intInput("Enter age");
        String emergencyContact = stringInput("Enter emergency contact number");
        int gradeLevel = intInput("Enter GradeLevel");
        return new Learner(firstName, lastName, gender, age, emergencyContact, gradeLevel);
    }
}
