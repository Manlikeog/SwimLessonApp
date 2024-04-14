package swimlessonapp.repository;


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

    public Lesson getLessonById(int lessonId) {
        Optional<Lesson> optionalLesson = listOfLesson.stream()
                .filter(lesson -> lesson.getId() == lessonId)
                .findFirst();
        return optionalLesson.orElse(null);
    }

}
