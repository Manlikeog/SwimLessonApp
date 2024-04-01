package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.LessonRepository;

import java.util.List;

public class BookingController {

    private final LessonRepository lessonRepository = LessonRepository.getInstance();
    private static final LessonController manageLesson = LessonController.getInstance();
    private static final LearnerController manageLearner = LearnerController.getInstance();
    static Config config = new Config();

    public void bookLesson(List<Lesson> choiceLessons) {
        int lessonIndex = config.intInput(""" 
                Select Lesson to book above!!
                Input Lesson ID:""");
        Lesson selectedLesson = lessonRepository.getLessonById(lessonIndex);
        Learner user = manageLearner.getLearner();
        if (selectedLesson != null) {
            manageLesson.addLearnerToLesson(user, selectedLesson);

            System.out.println("You have selected the lesson: " + selectedLesson.getDay() + " " + selectedLesson.getTime());
            // Proceed with any further actions related to the selected lesson
        } else {
            System.out.println("Invalid lesson index!");
        }

    }
}
