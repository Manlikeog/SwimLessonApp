package swimlessonapp.view;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static swimlessonapp.Config.stringInput;
import static swimlessonapp.Config.stringOutput;

public class TimeTableView {

    public void viewTimeTableByCriteria(String prompt, Function<Lesson, ?> criteriaExtractor, List<Lesson> lessons) {
        String userInput = stringInput(prompt);
        List<Lesson> filteredLessons = filterLessons(lesson -> criteriaExtractor.apply(lesson).toString().equalsIgnoreCase(userInput), lessons);
        if (filteredLessons.isEmpty()) {
            stringOutput("There is no available lesson for " + userInput + " this week");
        } else {
           viewLessons(prompt.substring(6), filteredLessons); // Displaying based on the provided criteria
        }
    }

    private List<Lesson> filterLessons(Predicate<Lesson> predicate, List<Lesson> lessons) {
        return lessons.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    public void viewLessons(String option, List<Lesson> lessons) {
        printLessons(option, lessons);
    }

    private void printLessons(String title, List<Lesson> lessons) {
       stringOutput("Available Lessons for " + title + ":");

        lessons.sort(Comparator.comparing(lesson -> Arrays.asList("Monday", "Wednesday", "Friday", "Saturday").indexOf(lesson.getDay())));

        System.out.printf("%-10s%-10s%-20s%-20s%-15s%-70s%-15s%s%n", "LessonID",
                "Day", "Time", "Coach", "Participants", "Learners", "Class Size", "Grade Level");

        for (Lesson lesson : lessons) {
            String learners = getLearnersString(lesson.getLearners());
            System.out.printf("%-10s%-10s%-20s%-20s%-15s%-70s%-15s%s%n", lesson.getId(),
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


    public void displayBookings(List<Book> bookings) {
        System.out.println("Available Bookings:");
        System.out.printf("%-12s%-15s%-10s%-20s%-20s%-10s%-10s%-10s%-10s%n",
                "BookingID", "User", "Day", "Time", "Coach", "Grade", "Status", "Review", "Rating");
        for (Book book : bookings) {
            System.out.printf("%-12s%-15s%-10s%-20s%-20s%-10s%-10s%-10s%-10s%n",
                    book.getId(),
                    book.getLearner().getFirstName() + " " + book.getLearner().getLastName(),
                    book.getLesson().getDay(),
                    book.getLesson().getTime(),
                    book.getLesson().getCoach().name(),
                    book.getLesson().getGradeLevel(),
                    book.getStatus(),
                    book.getReview(),
                    book.getRating());
        }
    }
}
