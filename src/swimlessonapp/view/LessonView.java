package swimlessonapp.view;


import swimlessonapp.repository.LessonRepository;



public class LessonView {

    public static void printLessonsForWeek(LessonRepository storedLessons) {
        System.out.println("Available Lessons:");
        storedLessons.getAllLessons().forEach(lesson -> System.out.println("Day: " + lesson.getDay() +
                ", Time: " + lesson.getTime() + " "  +
                ", Coach: " + lesson.getCoach().getName() +
                ", Participants: " + lesson.getLearners().length +
                ", Class size: " + lesson.getMaxLearners() +
                ", GradeLevel: " + lesson.getGradeLevel()
        ));
    }
}
