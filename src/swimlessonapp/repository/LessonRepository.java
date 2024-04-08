package swimlessonapp.repository;


import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class LessonRepository {
    private static List<Lesson> listOfLesson = new ArrayList<>();
    private static LessonRepository instance;

    public static LessonRepository getInstance() {
        if (instance == null) {
            instance = new LessonRepository();
        }
        return instance;
    }

    public  List<Lesson> getListOfLesson() {
        return listOfLesson;
    }
    public  void setListOfLesson(List<Lesson> listOfLesson) {
        LessonRepository.listOfLesson = listOfLesson;
    }

    public boolean hasLesson(Lesson lesson) {
      return   !listOfLesson.contains(lesson);

    }
    public Lesson getLessonById(int lessonId) {
        Optional<Lesson> optionalLesson = listOfLesson.stream()
                .filter(lesson -> lesson.getId() == lessonId)
                .findFirst();
        return optionalLesson.orElse(null);
    }

    public List<Lesson> getListOfLessonsForLearner(Learner learner) {
        List<Lesson> lessonsForLearner = new ArrayList<>();
        // Assuming lesson has a list of learners, and we're checking if the learner is enrolled in the lesson
        for (Lesson lesson : listOfLesson) {
            if (lesson.getLearners().contains(learner)) {
                lessonsForLearner.add(lesson);
            }
        }
        return lessonsForLearner;
    }
}
