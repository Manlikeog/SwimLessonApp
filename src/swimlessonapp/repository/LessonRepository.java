package swimlessonapp.repository;


import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class LessonRepository {
    private static final List<Lesson> LIST_OF_LESSON = new ArrayList<>();
    private static LessonRepository instance;

    public static LessonRepository getInstance() {
        if (instance == null) {
            instance = new LessonRepository();
        }
        return instance;
    }

    public  List<Lesson> getListOfLesson() {
        return LIST_OF_LESSON;
    }
    public  void setListOfLesson(List<Lesson> listOfLesson) {
        LIST_OF_LESSON.addAll(listOfLesson);
    }

    public Lesson getLessonById(int lessonId) {
        Optional<Lesson> optionalLesson = LIST_OF_LESSON.stream()
                .filter(lesson -> lesson.getId() == lessonId)
                .findFirst();
        return optionalLesson.orElse(null);
    }

}
