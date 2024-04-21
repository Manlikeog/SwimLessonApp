package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.repository.LessonRepository;

import java.util.*;

public class TimeTableController {
    private final LearnerRepository learnerRepository = LearnerRepository.getInstance();
    private final CoachRepository coachRepository = CoachRepository.getInstance();
    private final LessonRepository lessonRepository = LessonRepository.getInstance();
    private final BookingRepository bookingRepository = BookingRepository.getInstance();

    private static final int WEEKS_IN_SEMESTER = 4;
    private static final int MAX_LESSONS_PER_DAY = 3;
    private static final int MAX_LESSONS_ON_SATURDAY = 2;
    private static final int MIN_GRADE_LEVEL = 1;
    private static final int MAX_GRADE_LEVEL = 5;

    public void generateWeekTimetable() {
        List<Lesson> lessons = new ArrayList<>();
        Map<String, String[]> timeSlots = createTimeSlots();
        Random random = new Random();

        for (int week = 1; week <= WEEKS_IN_SEMESTER; week++) {
            int month = week > WEEKS_IN_SEMESTER / 2 ? 2 : 1;
            Map<String, Integer> lessonsScheduledPerDay = initializeLessonsScheduledPerDay(); // Initialize lessons scheduled per day for each week
            List<Lesson> generatedLessons = generateLessons(timeSlots, lessonsScheduledPerDay, random, week, month);
            lessons.addAll(generatedLessons);
            if (week < WEEKS_IN_SEMESTER) {
                markPreviousBookings(generatedLessons, random, week, month); // Mark previous bookings for all generated lessons except for the last week
            }
            // Add generated lessons to the main list
        }

        lessonRepository.setListOfLesson(lessons); // Set the list of lessons to the repository after generating lessons for all weeks
    }


    private Map<String, String[]> createTimeSlots() {
        Map<String, String[]> timeSlots = new HashMap<>();
        timeSlots.put("Monday", new String[]{"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"});
        timeSlots.put("Wednesday", new String[]{"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"});
        timeSlots.put("Friday", new String[]{"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"});
        timeSlots.put("Saturday", new String[]{"2:00 PM - 3:00 PM", "3:00 PM - 4:00 PM"});
        return timeSlots;
    }

    private Map<String, Integer> initializeLessonsScheduledPerDay() {
        Map<String, Integer> lessonsScheduledPerDay = new HashMap<>();
        lessonsScheduledPerDay.put("Monday", 0);
        lessonsScheduledPerDay.put("Wednesday", 0);
        lessonsScheduledPerDay.put("Friday", 0);
        lessonsScheduledPerDay.put("Saturday", 0);
        return lessonsScheduledPerDay;
    }

    private List<Lesson> generateLessons(Map<String, String[]> timeSlots, Map<String, Integer> lessonsScheduledPerDay, Random random, int week, int month) {
        List<Lesson> lessons = new ArrayList<>();
        List<Coach> coaches = coachRepository.getAllCoaches();
        for (String day : new String[]{"Monday", "Wednesday", "Friday"}) {
            while (lessonsScheduledPerDay.get(day) < MAX_LESSONS_PER_DAY) {
                String time = timeSlots.get(day)[lessonsScheduledPerDay.get(day)];
                Coach coach = coaches.get(random.nextInt(coaches.size()));
                int gradeLevel = random.nextInt(MAX_GRADE_LEVEL - MIN_GRADE_LEVEL + 1) + MIN_GRADE_LEVEL;
                if (isGradeLevelScheduled(lessons, day, gradeLevel)) {
                    Lesson lesson = createLesson(day, time, gradeLevel, coach, random, week, month);
                    lessons.add(lesson);
                    lessonsScheduledPerDay.put(day, lessonsScheduledPerDay.get(day) + 1);

                }
            }
        }

        while (lessonsScheduledPerDay.get("Saturday") < MAX_LESSONS_ON_SATURDAY) {
            String time = timeSlots.get("Saturday")[lessonsScheduledPerDay.get("Saturday")];
            Coach coach = coaches.get(random.nextInt(coaches.size()));
            int gradeLevel = random.nextInt(MAX_GRADE_LEVEL - MIN_GRADE_LEVEL) + MIN_GRADE_LEVEL;
            if (isGradeLevelScheduled(lessons, "Saturday", gradeLevel)) {
                Lesson lesson = createLesson("Saturday", time, gradeLevel, coach, random, week, month);
                lessons.add(lesson);
                lessonsScheduledPerDay.put("Saturday", lessonsScheduledPerDay.get("Saturday") + 1);
            }
        }

        return lessons;
    }

    private boolean isGradeLevelScheduled(List<Lesson> lessons, String day, int gradeLevel) {
        return lessons.stream()
                .noneMatch(lesson -> lesson.getDay().equals(day) && lesson.getGradeLevel() == gradeLevel);
    }

    private Lesson createLesson(String day, String time, int gradeLevel, Coach coach, Random random, int week, int month) {
        List<Learner> learners = learnerRepository.getAllLearners();

        Lesson lesson = new Lesson(day, time, gradeLevel, coach, week, month);
        int initialLearners = random.nextInt(3) + 1;
        Collections.shuffle(learners);
        for (Learner learner : learners) {
            if (learner.getCurrentGradeLevel() == gradeLevel || learner.getCurrentGradeLevel() == gradeLevel - 1) {
                if (!lesson.isLearnerEnrolled(learner)) {
                    lesson.addLearner(learner);
                    Book booking = new Book(learner, lesson);
                    bookingRepository.addBooking(booking);
                    initialLearners--;
                    if (initialLearners == 0) {
                        break;
                    }
                }
            }
        }
        return lesson;
    }

    private void markPreviousBookings(List<Lesson> lessons, Random random, int week, int month) {
        for (Lesson lesson : lessons) {
            // Get all bookings for this lesson
            List<Book> bookings = bookingRepository.getBookingsForLesson(lesson);

            // Randomly mark bookings as attended or canceled
            for (Book booking : bookings) {
                booking.setWeek(week);
                booking.setMonth(month);
                boolean isAttended = random.nextBoolean();
                // Randomly determine if booking is attended
                if (isAttended) {
                    if (booking.getLearner().getCurrentGradeLevel() == booking.getLesson().getGradeLevel()) {
                        // Adding rating for coach after the lesson is booked
                        int rating = random.nextInt(1,5) + 1;
                        booking.setStatus("attended");
                        booking.setRating(rating);
                        booking.setReview("Good");
                    }
                } else {
                    booking.setStatus("canceled");
                }
            }
        }
    }
}
