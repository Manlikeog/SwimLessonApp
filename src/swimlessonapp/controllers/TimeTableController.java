package swimlessonapp.controllers;

import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.repository.LessonRepository;
import swimlessonapp.view.TimeTableView;

import java.util.*;

import static swimlessonapp.Config.*;

public class TimeTableController {
    LearnerRepository learnerRepository = LearnerRepository.getInstance();
    CoachRepository coachRepository = CoachRepository.getInstance();
    LessonRepository lessonRepository = LessonRepository.getInstance();
    TimeTableView view = new TimeTableView();

    public void generateWeekTimetable() {
        List<Lesson> lessons = new ArrayList<>();
        Map<String, String[]> timeSlots = createTimeSlots();

        Map<String, Integer> lessonsScheduledPerDay = initializeLessonsScheduledPerDay();

        generateLessons(lessons, timeSlots, lessonsScheduledPerDay);

        lessonRepository.setListOfLesson(lessons);

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
                    initialLearners--;
                    if (initialLearners == 0) {
                        break;
                    }
                }
            }
        }

        return lesson;
    }

    public boolean viewTimeTable() {
        if (lessonRepository.getListOfLesson().isEmpty()) {
            generateWeekTimetable();
            viewTimeTable();
        } else {
            printTimeTable();
            return true;
        }
        return false;
    }

    public void printTimeTable() {
        List<Lesson> lessons = lessonRepository.getListOfLesson();
        stringOutput("""
                Select an option to view the timetable:
                1. View timetable for a specific day
                2. View timetable for a specific grade level
                3. View timetable for a specific coach
                """);

        int choice = intInput("Enter your choice: ");
        switch (choice) {
            case 1:
                view.viewTimeTableByCriteria("Enter the day (e.g., Monday): ", Lesson::getDay, lessons);
                break;
            case 2:
                view.viewTimeTableByCriteria("Enter the grade level: ", Lesson::getGradeLevel, lessons);
                break;
            case 3:
                view.viewTimeTableByCriteria("Enter the coach's name: ", lesson -> lesson.getCoach().name(), lessons);
                break;
            default:
                stringOutput("Invalid choice!");
        }
    }


}
