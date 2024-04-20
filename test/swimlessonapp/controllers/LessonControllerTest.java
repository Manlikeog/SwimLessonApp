package swimlessonapp.controllers;

import org.junit.jupiter.api.Test;
import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.LessonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonControllerTest {

    @Test
    public void addLearnerToLesson_LearnerAdded_ReturnsTrue() {
        // Mocking necessary dependencies
        LessonRepository lessonRepository = LessonRepository.getInstance();
        Learner learner = new Learner("adwa", "edwa", 'M', 8, "1234567890", 1, 1);
        Lesson lesson = new Lesson("1", "Monday", 2, new Coach("Layo"), 4, 2);

        // Creating a test instance of LessonController
        LessonController lessonControllerUnderTest = new LessonController();

        // Adding the lesson to the repository
        lessonRepository.setListOfLesson(List.of(lesson));

        // Adding the learner to the lesson
        boolean result = lessonControllerUnderTest.addLearnerToLesson(learner, lesson);

        // Asserting that the learner was added successfully
        assertTrue(result);
        assertEquals(1, lesson.getLearners().size());
        assertTrue(lesson.isLearnerEnrolled(learner));
    }

    @Test
    public void cancelLesson_LearnerCancelled_ReturnsTrue() {
        // Mocking necessary dependencies
        LessonRepository lessonRepository = LessonRepository.getInstance();
        Learner learner = new Learner("adwa", "edwa", 'M', 8, "1234567890", 1, 1);
        Lesson lesson = new Lesson("1", "Monday", 2, new Coach("Layo"), 4, 2);
        lesson.addLearner(learner);

        // Creating a test instance of LessonController
        LessonController lessonControllerUnderTest = new LessonController();

        // Adding the lesson to the repository
        lessonRepository.setListOfLesson(List.of(lesson));

        // Cancelling the lesson for the learner
        boolean result = lessonControllerUnderTest.cancelLesson(learner, lesson);

        // Asserting that the cancellation was successful
        assertTrue(result);
        assertEquals(0, lesson.getLearners().size());
        assertFalse(lesson.isLearnerEnrolled(learner));
    }

    @Test
    void selectLesson_ValidInput_ReturnsLesson() {
        // Mocking necessary dependencies
        LessonRepository lessonRepository = new LessonRepository();

        // Creating a test instance of LessonController
        LessonController lessonController = new LessonController();

        // Adding a test lesson to the repository
        Lesson testLesson = new Lesson("Monday", "4:00 PM - 5:00 PM", 3, new Coach("Layo"), 3, 2);
        testLesson.setId(10);
        lessonRepository.setListOfLesson(List.of(testLesson));

        // Calling selectLesson() method
        Lesson selectedLesson = lessonController.getLessonById(10);

        // Asserting that the returned value is not null
        assertNotNull(selectedLesson);
    }
}