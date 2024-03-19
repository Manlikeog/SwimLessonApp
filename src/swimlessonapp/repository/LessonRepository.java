//package swimlessonapp.repository;
//
//import swimlessonapp.model.Coach;
//import swimlessonapp.model.Learner;
//import swimlessonapp.model.Lesson;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//
//public class LessonRepository {
//    private static LessonRepository instance;
//    public static LessonRepository  getInstance() {
//        if (instance == null) {
//            instance = new LessonRepository();
//        }
//        return instance;
//    }
//
////    public List<Lesson> generateTimetable(boolean repeatWeekly) {
////        List<Lesson> timetable = new ArrayList<>();
////
////
////        // Iterate over the weeks
////        for (int week = 1; week <= WEEKS; week++) {
////            // Generate lessons for the current week
//////            List<Lesson> lessonsForWeek = generateWeekTimetable(random);
////
////            // Add the lessons to the timetable
////            timetable.addAll(lessonsForWeek);
////
////            // If not repeating weekly, and it's not the last week, clear the lessons
////            if (!repeatWeekly && week != WEEKS) {
////                timetable.clear();
////            }
////        }
////
////        return timetable;
////    }
//
//    private void generateWeekTimetable(Random random) {
//        List<Lesson> lessons = new ArrayList<>();
//        List<Coach> coaches = coachRepository.getAllCoaches();
//        List<Learner> learners = learnerRepository.getAllLearners();
//        Random random = new Random();
//        int LESSONS_PER_WEEK = 11;
//
//        // Define time slots for each day
//        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
//        String[][] timeSlots = {
//                {"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"}, // Monday, Wednesday, Friday
//                {"2:00 PM - 3:00 PM", "3:00 PM - 4:00 PM"} // Saturday
//        };
//
//        // Distribute lessons evenly across coaches and grades
//        for (int i = 0; i < LESSONS_PER_WEEK; i++) {
//            Coach coach = coaches.get(random.nextInt(coaches.size()));
//            String day = days[random.nextInt(days.length)];
//            String time = timeSlots[day.equals("Saturday") ? 1 : 0][random.nextInt(2)]; // Saturday has different time slots
//            int gradeLevel = random.nextInt(4) + 1; // Random grade level
//            Learner learner = learners.get(random.nextInt(learners.size()));
//
//            // Check if the grade level already has a class scheduled on the same day
//            boolean hasClass = lessons.stream()
//                    .anyMatch(lesson -> lesson.getDay().equals(day) && lesson.getGradeLevel() == gradeLevel);
//
//            if (hasClass) {
//                // Skip scheduling this lesson if the grade level already has a class scheduled on the same day
//                continue;
//            }
//
//            // Determine the initial number of learners for this lesson (between 1 and 3)
//            int initialLearners = random.nextInt(3) + 1;
//
//            // Create a new lesson and add it to the list
//            Lesson lesson = new Lesson(day, time, gradeLevel, coach);
//
//            // Add the initial learners to the lesson
//            for (int j = 0; j < initialLearners && j < learners.size(); j++) {
//                // Check if the learner's grade matches the grade level of the lesson
//                if (!lesson.isLearnerEnrolled(learners.get(j))) {
//                    lesson.addLearner(learners.get(j));
//                }
//            }
//
//            lessons.add(lesson);
//        }
//
//        listOfLesson = lessons;
//    }
//
//    public List<Lesson> getAllLessons() {
//        if (listOfLesson.isEmpty()) {
//            generateWeekTimetable();
//        }
//        return listOfLesson;
//    }
//
//    public void printLessonsForWeek() {
//        System.out.println("Available Lessons:");
//        getAllLessons().forEach(lesson -> {
//            System.out.print("Day: " + lesson.getDay() +
//                    ", Time: " + lesson.getTime() +
//                    ", Coach: " + lesson.getCoach().name() +
//                    ", Participants:" + lesson.getLearners().size());
//            // Print each learner's name
//            lesson.getLearners().forEach(learner -> System.out.print(learner.getFirstName()));
//            System.out.println(", Class size: " + lesson.getMaxLearners() +
//                    ", GradeLevel: " + lesson.getGradeLevel());
//        });
//    }
//}
