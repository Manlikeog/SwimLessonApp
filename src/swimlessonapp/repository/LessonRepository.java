package swimlessonapp.repository;

import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LessonRepository {
    private static final int LESSONS_PER_WEEK = 11; // Assuming 11 lessons per week
    private static final int WEEKS = 4; // Total number of weeks
    private final CoachRepository coachRepository;
    private final LearnerRepository learnerRepository;
    private static List<Lesson> listOfLesson = new ArrayList<>();

    Random random = new Random();
    public LessonRepository(CoachRepository coachRepository, LearnerRepository learnerRepository) {
        this.coachRepository = coachRepository;
        this.learnerRepository = learnerRepository;
    }

//    public List<Lesson> generateTimetable(boolean repeatWeekly) {
//        List<Lesson> timetable = new ArrayList<>();
//
//
//        // Iterate over the weeks
//        for (int week = 1; week <= WEEKS; week++) {
//            // Generate lessons for the current week
////            List<Lesson> lessonsForWeek = generateWeekTimetable(random);
//
//            // Add the lessons to the timetable
//            timetable.addAll(lessonsForWeek);
//
//            // If not repeating weekly, and it's not the last week, clear the lessons
//            if (!repeatWeekly && week != WEEKS) {
//                timetable.clear();
//            }
//        }
//
//        return timetable;
//    }

    private void generateWeekTimetable(Random random) {
        List<Lesson> lessons = new ArrayList<>();
        List<Coach> coaches = coachRepository.getAllCoaches();
        List<Learner> learners = learnerRepository.getAllLearners();

        // Distribute lessons evenly across coaches and grades
        for (int i = 0; i < LESSONS_PER_WEEK; i++) {
            Coach coach = coaches.get(random.nextInt(coaches.size()));
            String gradeLevel = "Grade " + (random.nextInt(4) + 1); // Random grade level

            // Generate a random day and time for the lesson
            String day = generateRandomDay(random);
            String time = generateRandomTime(random);

            // Determine the initial number of learners for this lesson (between 1 and 3)
            int initialLearners = random.nextInt(3) + 1;

            // Create a new lesson and add it to the list
            Lesson lesson = new Lesson(day, time, gradeLevel, coach);

            // Add the initial learners to the lesson
            for (int j = 0; j < initialLearners && j < learners.size(); j++) {
                // Check if the learner's grade matches the grade level of the lesson
                if (learners.get(j).getCurrentGradeLevel() == Integer.parseInt(gradeLevel.split(" ")[1])) {
                    lesson.addLearner(learners.get(j));
                }
            }

            lessons.add(lesson);
        }

        listOfLesson = lessons;
    }

    public List<Lesson> getAllLessons() {
       generateWeekTimetable(random);
        return listOfLesson;
    }

    private String generateRandomDay(Random random) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return days[random.nextInt(days.length)];
    }

    private String generateRandomTime(Random random) {
        int hour = random.nextInt(4) + 16; // Random hour between 4 PM and 7 PM
        return String.format("%d:00 PM - %d:00 PM", hour, hour + 1);
    }
}
