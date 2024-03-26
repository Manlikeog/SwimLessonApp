package swimlessonapp.repository;


import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;


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

    public boolean hasLesson(String day, String time, int gradeLevel) {
        for (Lesson lesson : listOfLesson) {
            if (lesson.getDay().equals(day) && lesson.getTime().equals(time) && lesson.getGradeLevel() == gradeLevel) {
                return false;
            }
        }
        return true;
    }
}
