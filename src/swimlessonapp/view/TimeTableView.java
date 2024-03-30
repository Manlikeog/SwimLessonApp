package swimlessonapp.view;

import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.TimeTableRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TimeTableView {

    private static final TimeTableRepository timeTable = new TimeTableRepository();

    public static void printLessonsForWeek() {
        List<Lesson> lessons = timeTable.getAllLessons();
        System.out.println("Available Lessons:");

        // Sort the lessons based on the order of days
        lessons.sort(Comparator.comparing(lesson -> Arrays.asList("Monday", "Wednesday", "Friday", "Saturday").indexOf(lesson.getDay())));

        // Print header
        System.out.printf("%-10s%-20s%-20s%-15s%-50s%-15s%s%n",
                "Day", "Time", "Coach", "Participants", "Learners", "Class Size", "Grade Level");

        // Print lessons
        for (Lesson lesson : lessons) {
            StringBuilder learnerNames = new StringBuilder();
            for (Learner learner : lesson.getLearners()) {
                learnerNames.append(learner.getFirstName()).append(" ").append(learner.getLastName()).append(", ");
            }
            learnerNames.delete(learnerNames.length() - 2, learnerNames.length()); // Remove the extra ", " at the end

            // Pad learner names to the maximum length
            String paddedLearnerNames = String.format("%-50s", learnerNames);

            System.out.printf("%-10s%-20s%-20s%-15s%-50s%-15s%s%n",
                    lesson.getDay(), lesson.getTime(), lesson.getCoach().name(),
                    lesson.getLearners().size(), paddedLearnerNames,
                    lesson.getMaxLearners(), lesson.getGradeLevel());
        }
    }
}
