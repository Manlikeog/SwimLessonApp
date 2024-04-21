package swimlessonapp.view;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static swimlessonapp.Config.*;

public class UserInteraction {

    public boolean viewTimeTableByCriteria(String prompt, Function<Lesson, ?> criteriaExtractor, List<Lesson> lessons) {
        String userInput = stringInput(prompt);
        List<Lesson> filteredLessons = filterLessons(lesson -> criteriaExtractor.apply(lesson).toString().equalsIgnoreCase(userInput), lessons);
        if (filteredLessons.isEmpty()) {
            printResult(false,"There is no available lesson for " + userInput + " this week");
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
        int currentWeek = -1;
        printResult(true,"Available Lessons for " + title + ":");


        for (Lesson lesson : lessons) {
            if(lesson.getWeek() != currentWeek){
                // New week encountered, display week header
                stringOutput("Lesson for Week " + lesson.getWeek() + ":");
                System.out.printf("%-10s%-10s%-20s%-15s%-20s%-15s%-70s%-15s%s%n", "LessonID",
                        "Day", "Time", "Week/Month", "Coach", "Participants", "Learners", "Class Size", "Grade Level");
                currentWeek = lesson.getWeek();
            }
            String learners = getLearnersString(lesson.getLearners());
            System.out.printf("%-10s%-10s%-20s%-15s%-20s%-15s%-70s%-15s%s%n", lesson.getId(),
                    lesson.getDay(), lesson.getTime(), lesson.getWeek() + "/" + lesson.getMonth(), lesson.getCoach().name(),
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
        int currentWeek = -1;

        for (Book book : bookings) {
            if (book.getWeek() != currentWeek) {
                // New week encountered, display week header
                stringOutput("Booking for Week " + book.getWeek() + ":");
                System.out.printf("%-12s%-15s%-10s%-20s%-15s%-20s%-10s%-10s%-10s%n",
                        "BookingID", "User", "Day", "Time", "Week/Month", "Coach", "Grade", "Status", "Rating");

                currentWeek = book.getWeek();
            }
            System.out.printf("%-12s%-15s%-10s%-20s%-15s%-20s%-10s%-10s%-10s%n",
                    book.getId(),
                    book.getLearner().getFirstName() + " " + book.getLearner().getLastName(),
                    book.getLesson().getDay(),
                    book.getLesson().getTime(),
                    book.getWeek() + "/" + book.getMonth(),
                    book.getLesson().getCoach().name(),
                    book.getLesson().getGradeLevel(),
                    book.getStatus(),
                    book.getRating());
        }
    }

    public void printTimeTable(List<Lesson> lessons) {
        boolean correctChoice;
        stringOutput("""
                Select an option to view the timetable:
                1. View timetable for a specific day
                2. View timetable for a specific grade level
                3. View timetable for a specific coach
                """);

        do {
            correctChoice = viewChoice(lessons);
        } while (!correctChoice);
    }

    public boolean viewChoice(List<Lesson> lessons) {
        int choice = intInput("Enter view choice:");
        return switch (choice) {
            case 1 -> viewTimeTableByCriteria("Enter the day (e.g., Monday)", Lesson::getDay, lessons);
            case 2 -> viewTimeTableByCriteria("Enter the grade level", Lesson::getGradeLevel, lessons);
            case 3 -> viewTimeTableByCriteria("Enter the coach's Full name(Timi oguntade)", lesson -> lesson.getCoach().name(), lessons);
            default -> {
                printResult(false,"Invalid choice!");
                yield false;
            }
        };
    }

    public void printLearnerInfo(Learner learner) {
        String columnBuilder = "First Name: " + learner.getFirstName() + "\n" +
                "Last Name: " + learner.getLastName() + "\n" +
                "Gender: " + learner.getGender() + "\n" +
                "Age: " + learner.getAge() + "\n" +
                "Phone Number: " + learner.getEmergencyContact() + "\n" +
                "User ID: " + learner.getUserId() + "\n" +
                "Grade Level: " + learner.getCurrentGradeLevel() + "\n\n";
        stringOutput(columnBuilder);
    }

    public void printLearnersInRow(List<Learner> listOfLearners) {
        stringOutput("Available Learners:");
        // Sort the list by user ID
        listOfLearners.sort(Comparator.comparingInt(Learner::getUserId));

        StringBuilder rowBuilder = new StringBuilder();
        for (Learner learner : listOfLearners) {
            rowBuilder.append(" - User ID: ").append(learner.getUserId()).append(", ")
                    .append("Name: ").append(learner.getFirstName()).append(" ").append(learner.getLastName()).append(", ")
                    .append("Gender: ").append(learner.getGender()).append("\n");
        }
        stringOutput(rowBuilder.toString());
    }

    public Learner learnerDetailsInput() {
        String firstName = stringInput("Enter first name").toUpperCase();
        String lastName = stringInput("Enter last name").toUpperCase();
        char gender = charInput("Enter gender (M/F/E(EXIT))");
        int age = intInput("Enter age");
        String emergencyContact = stringInput("Enter emergency contact number");
        int gradeLevel = intInput("Enter GradeLevel");
        return new Learner(firstName, lastName, gender, age, emergencyContact, gradeLevel, 0);
    }
}
