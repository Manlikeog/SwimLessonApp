package swimlessonapp.controllers;
import swimlessonapp.Config;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.LessonRepository;
import swimlessonapp.view.TimeTableView;




public class LessonController {
    private final LessonRepository lessonRepository = LessonRepository.getInstance();
    static Config config = new Config();
    private static LessonController instance;

    public static LessonController getInstance() {
        if (instance == null) {
            instance = new LessonController();
        }
        return instance;
    }

    // Method to get all lessons for a given week
    public void viewAvailableLessons(){
        TimeTableView.viewTimeTable();
    }

    public void addLearnerToLesson(Learner learner, Lesson lesson) {
        if (lesson.getLearners().size() < lesson.getMaxLearners()) {
            if (lesson.isLearnerEnrolled(learner)) {
                lesson.addLearner(learner);
                System.out.println(learner.getFirstName() + " added to lesson on " + lesson.getDay() + " at " + lesson.getTime());
            } else {
                System.out.println(learner.getFirstName() + " is already enrolled in this lesson.");
            }
        } else {
            System.out.println("The lesson is already full. Cannot add more learners.");
        }
    }

    // Method to cancel a lesson for a learner
    public void cancelLesson(Learner learner, Lesson lesson) {
        if (lesson.isLearnerEnrolled(learner)) {
            lesson.removeLearner(learner);
            System.out.println(learner.getFirstName() + " canceled the lesson on " + lesson.getDay() + " at " + lesson.getTime());
        } else {
            System.out.println("You are not booked for this lesson.");
        }
    }

    public boolean checkGradeLevel(Learner learner, Lesson lesson){
        return learner.getCurrentGradeLevel() == lesson.getGradeLevel() || learner.getCurrentGradeLevel() == lesson.getGradeLevel() - 1;
    }

    public Lesson getLessonById(){
      int  lessonIndex = config.intInput(""" 
                Select Lesson to book above!!
                Input Lesson ID:""");
      Lesson selectedLesson = lessonRepository.getLessonById(lessonIndex);
       if(selectedLesson == null){
            System.out.println("Invalid lesson index!");
            getLessonById();
        } else {
           selectedLesson;
        }

    }

//
//    // Method to mark attendance for a lesson
//    public void markAttendance(Lesson lesson, Learner learner) {
//        if (lesson.isLearnerEnrolled(learner)) {
//            lesson.markAttendance(learner);
//            lessonRepository.updateLesson(lesson);
//            System.out.println(learner.getFirstName() + " attended the lesson on " + lesson.getDay() + " at " + lesson.getTime());
//        } else {
//            System.out.println("You are not enrolled for this lesson.");
//        }
//    }
//
//    // Method to get all lessons for a given week
//    public List<Lesson> getLessonsForWeek(int weekNumber) {
//        return lessonRepository.getLessonsForWeek(weekNumber);
//    }
}