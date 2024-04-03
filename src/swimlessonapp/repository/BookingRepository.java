package swimlessonapp.repository;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private static final List<Book> availableBookings = new ArrayList<>();

    private final LessonRepository lessonRepository = LessonRepository.getInstance();

    private static BookingRepository instance;
    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepository();
        }
        return instance;
    }


    public List<Book> getAllBookings() {
        return availableBookings;
    }

    public void addBookingsForLearner(Learner learner) {
        List<Lesson> lessons = lessonRepository.getListOfLesson(); // Assuming you have a method in LessonRepository to get lessons for a particular learner
        for (Lesson lesson : lessons) {
            if(lesson.getLearners().equals(learner))
            {
                Book booking = new Book(learner, lesson);
                availableBookings.add(booking);
            }
        }
    }
}
