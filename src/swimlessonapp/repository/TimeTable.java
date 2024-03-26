package swimlessonapp.repository;

import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.*;

public class TimeTable {
    private final LearnerRepository learnerRepository = LearnerRepository.getInstance();
    private final CoachRepository coachRepository = CoachRepository.getInstance();
    private final LessonRepository lessonRepository = LessonRepository.getInstance();

    public List<Lesson> generateWeekTimetable() {

        List<Lesson> lessons = new ArrayList<>();
        List<Coach> coaches = coachRepository.getAllCoaches();
        Random random = new Random();

        Map<String, String[]> timeSlots = new HashMap<>();
        timeSlots.put("Monday", new String[]{"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"});
        timeSlots.put("Wednesday", new String[]{"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"});
        timeSlots.put("Friday", new String[]{"4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM"});
        timeSlots.put("Saturday", new String[]{"2:00 PM - 3:00 PM", "3:00 PM - 4:00 PM"});

        Map<String, Integer> lessonsScheduledPerDay = new HashMap<>();
        lessonsScheduledPerDay.put("Monday", 0);
        lessonsScheduledPerDay.put("Wednesday", 0);
        lessonsScheduledPerDay.put("Friday", 0);
        lessonsScheduledPerDay.put("Saturday", 0);

        for (String day : new String[]{"Monday", "Wednesday", "Friday"}) {
            while (lessonsScheduledPerDay.get(day) < 3) {
                String time = timeSlots.get(day)[lessonsScheduledPerDay.get(day)];
                Coach coach = coaches.get(random.nextInt(coaches.size()));
                int gradeLevel = random.nextInt(5) + 1;
                boolean hasClass = lessons.stream()
                        .anyMatch(lesson -> lesson.getDay().equals(day)  && lesson.getGradeLevel() == gradeLevel);
                if (!hasClass) {
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
            boolean hasClass = lessons.stream()
                    .anyMatch(lesson -> lesson.getDay().equals("Saturday") && lesson.getGradeLevel() == gradeLevel);
            if (!hasClass) {
                Lesson lesson = createLesson("Saturday", time, gradeLevel, coach, random);
                lessons.add(lesson);
                lessonsScheduledPerDay.put("Saturday", lessonsScheduledPerDay.get("Saturday") + 1);
            }
        }

        lessonRepository.setListOfLesson(lessons);
        return lessons;
    }

    private Lesson createLesson(String day, String time, int gradeLevel, Coach coach, Random random) {
        List<Learner> learners = learnerRepository.getAllLearners();
        Lesson lesson = new Lesson(day, time, gradeLevel, coach);
        int initialLearners = random.nextInt(3) + 1;

        for (Learner learner : learners) {
            // Check if the learner's grade matches the lesson's grade or is lower by 1
            if (learner.getCurrentGradeLevel() == gradeLevel || learner.getCurrentGradeLevel() == gradeLevel - 1) {
                // Add the learner to the lesson if they're not already enrolled
                if (!lesson.isLearnerEnrolled(learner)) {
                    lesson.addLearner(learner);
                    initialLearners--; // Decrement the number of initial learners to add
                    if (initialLearners == 0) {
                        break; // Break the loop if all initial learners are added
                    }
                }
            }
        }

        return lesson;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.getListOfLesson().isEmpty() ? generateWeekTimetable() : lessonRepository.getListOfLesson();
    }

}
