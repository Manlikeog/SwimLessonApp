package swimlessonapp.repository;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {

    private static final List<Book> availableBookings = new ArrayList<>();

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

    public  void addBooking(Book book) {
        availableBookings.add(book);
    }
    public Book getBookingById(int bookId) {
        Optional<Book> optionalBook = availableBookings.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst();
        return optionalBook.orElse(null);
    }

    public List<Book> getAvailableBookingsForLearner(Learner learner) {
        List<Book> learnerBookings = new ArrayList<>();
        for (Book book : availableBookings) {
            if (book.getLearner().equals(learner)) {
                learnerBookings.add(book);
            }
        }
        return learnerBookings;
    }

    public List<Book> getLearnerBookingsByMonth(Learner learner, int month) {
        List<Book> learnerBookings = new ArrayList<>();
        for (Book book : availableBookings) {
            if (book.getLearner().equals(learner) && book.getMonth() == month) {
                learnerBookings.add(book);
            }
        }
        return learnerBookings;
    }

    public List<Book> getBookingsForLesson(Lesson lesson) {
        List<Book> lessonBookinga = new ArrayList<>();
        for (Book book : availableBookings) {
            if (book.getLesson().equals(lesson)) {
                lessonBookinga.add(book);
            }
        }
        return  lessonBookinga;
    }
}
