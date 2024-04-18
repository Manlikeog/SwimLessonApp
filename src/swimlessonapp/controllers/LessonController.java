package swimlessonapp.controllers;


import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

import static swimlessonapp.Config.*;


public class LessonController {
    private static LessonController instance;
    LessonRepository lessonRepository = LessonRepository.getInstance();

    public static LessonController getInstance() {
        if (instance == null) {
            instance = new LessonController();
        }
        return instance;
    }

    public boolean addLearnerToLesson(Learner learner, Lesson lesson) {
        if (lesson.getLearners().size() < lesson.getMaxLearners()) {
            if (!lesson.isLearnerEnrolled(learner)) {
                lesson.addLearner(learner);
                stringOutput(learner.getFirstName() + " added to lesson on " + lesson.getDay() + " at " + lesson.getTime());
            } else {
                printResult(false,"Can't book lesson as learner has been enrolled in the lesson ");
                return false;
            }
        } else {
           printResult(false,"The lesson is full. Cannot add more learners.");
            return false;
        }
        return true;
    }

    // Method to cancel a lesson for a learner
    public boolean cancelLesson(Learner learner, Lesson lesson) {
        if (lesson.isLearnerEnrolled(learner)) {
            lesson.removeLearner(learner);
            stringOutput(learner.getFirstName() + " canceled the lesson on " + lesson.getDay() + " at " + lesson.getTime());
        } else {
            printResult(false,"You are not booked for this lesson.");
            return false;
        }
        return true;
    }

    public boolean checkGradeLevel(Learner learner, Lesson lesson) {
        return learner.getCurrentGradeLevel() == lesson.getGradeLevel() || learner.getCurrentGradeLevel() == lesson.getGradeLevel() - 1;
    }

    public Lesson getLessonById(String text) {
        int lessonIndex = intInput(text);
        Lesson selectedLesson = lessonRepository.getLessonById(lessonIndex);
        if (selectedLesson == null) {
            printResult(false,"Invalid lesson index!");
            return getLessonById(text);
        } else {
            return selectedLesson;
        }

    }

    public List<Lesson> getAvailableLessons() {
        List<Lesson> lessons = lessonRepository.getListOfLesson();
        List<Lesson> currentWeekLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getWeek() == 4) {
                currentWeekLessons.add(lesson);
            }
        }
        return currentWeekLessons;
    }
}