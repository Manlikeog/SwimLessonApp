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
    LearnerRepository learnerRepository = LearnerRepository.getInstance();
    CoachRepository coachRepository = CoachRepository.getInstance();
    LessonRepository lessonRepository = LessonRepository.getInstance();
    BookingRepository bookingRepository = BookingRepository.getInstance();

    public void generateWeekTimetable() {
        List<Lesson> lessons = new ArrayList<>();
        Map<String, String[]> timeSlots = createTimeSlots();

        Map<String, Integer> lessonsScheduledPerDay = initializeLessonsScheduledPerDay();


        for (int week = 1; week <= 8; week++) {
            generateLessons(lessons, timeSlots, lessonsScheduledPerDay);
            if (week == 8) {
                lessonRepository.setListOfLesson(lessons);
            } else {
                simulateLearnerStatuses(week);

            }
        }
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

    private void generateLessons(List<Lesson> lessons, Map<String, String[]> timeSlots, Map<String, Integer> lessonsScheduledPerDay) {
        List<Coach> coaches = coachRepository.getAllCoaches();
        Random random = new Random();

        for (String day : new String[]{"Monday", "Wednesday", "Friday"}) {
            while (lessonsScheduledPerDay.get(day) < 3) {
                String time = timeSlots.get(day)[lessonsScheduledPerDay.get(day)];
                Coach coach = coaches.get(random.nextInt(coaches.size()));
                int gradeLevel = random.nextInt(5) + 1;
                if (isGradeLevelScheduled(lessons, day, gradeLevel)) {
                    Lesson lesson = createLesson(day, time, gradeLevel, coach, random);
                    lessons.add(lesson);
                    lessonsScheduledPerDay.put(day, lessonsScheduledPerDay.get(day) + 1);
                }
            }
        }

        while (lessonsScheduledPerDay.get("Saturday") < 2) {
            String time = timeSlots.get("Saturday")[lessonsScheduledPerDay.get("Saturday")];
            Coach coach = coaches.get(random.nextInt(coaches.size()));
            int gradeLevel = random.nextInt(4) + 1;
            if (isGradeLevelScheduled(lessons, "Saturday", gradeLevel)) {
                Lesson lesson = createLesson("Saturday", time, gradeLevel, coach, random);
                lessons.add(lesson);
                lessonsScheduledPerDay.put("Saturday", lessonsScheduledPerDay.get("Saturday") + 1);
            }
        }

        for (Coach coach : coaches) {
            List<Book> coachBookings = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getCoach().equals(coach)) {
                    for (Learner learner : lesson.getLearners()) {
                        for (Book booking : bookingRepository.getAllBookings()) {
                            if (booking.getLearner().equals(learner) && booking.getLesson().equals(lesson)) {
                                coachBookings.add(booking);
                            }
                        }
                    }
                }
            }
            coach.setBookings(coachBookings);
        }
    }

    private boolean isGradeLevelScheduled(List<Lesson> lessons, String day, int gradeLevel) {
        return lessons.stream()
                .noneMatch(lesson -> lesson.getDay().equals(day) && lesson.getGradeLevel() == gradeLevel);
    }

    private Lesson createLesson(String day, String time, int gradeLevel, Coach coach, Random random) {
        List<Learner> learners = learnerRepository.getAllLearners();
        Lesson lesson = new Lesson(day, time, gradeLevel, coach);
        int initialLearners = random.nextInt(3) + 1;
        Collections.shuffle(learners);
        for (Learner learner : learners) {
            if (learner.getCurrentGradeLevel() == gradeLevel || learner.getCurrentGradeLevel() == gradeLevel - 1) {
                if (lesson.isLearnerEnrolled(learner)) {
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

    private void simulateLearnerStatuses(int week) {
        Random random = new Random();
        List<Book> availableBooking = bookingRepository.getAllBookings();
        for (Book book : availableBooking) {
            int randomNumber = random.nextInt(10);
            int randomMonth = random.nextInt(1, 3);
            int randomRating = random.nextInt(1, 6);

                if (randomNumber < 6) { // 60% chance of booking
                    if(!Objects.equals(book.getStatus(), "attended") && !Objects.equals(book.getStatus(), "canceled")){
                        book.setStatus("booked");
                        book.setMonth(randomMonth);
                    }
                } else if (randomNumber < 9) { // 30% chance of attending the lesson
                    if (book.getLesson().getGradeLevel() <= book.getLearner().getCurrentGradeLevel() && !Objects.equals(book.getStatus(), "canceled")){
                        if(week < 8){
                            book.setStatus("attended");
                            book.setMonth(randomMonth);
                            book.setRating(randomRating);
                        }
                    }
                } else { // 10% chance of canceling the lesson
                    if(!Objects.equals(book.getStatus(), "attended") && !Objects.equals(book.getStatus(), "canceled")){
                        if(week < 8){
                            book.setStatus("canceled");
                            book.setMonth(randomMonth);
                        }

                    }
                }

        }
    }

}
