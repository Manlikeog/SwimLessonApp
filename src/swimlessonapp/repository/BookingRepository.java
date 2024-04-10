package swimlessonapp.repository;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {

    private static final List<Book> availableBookings = new ArrayList<>();

    LessonRepository lessonRepository = LessonRepository.getInstance();
    LearnerRepository learnerRepository = LearnerRepository.getInstance();

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


    public void generateBookingsForAllLearners() {
        List<Learner> learners = learnerRepository.getAllLearners();
        boolean bookingsGenerated = false;
        for (Learner learner : learners) { // Assuming you have a method to get all learners
            List<Lesson> lessons = lessonRepository.getListOfLessonsForLearner(learner);
            if (!lessons.isEmpty()) {
                for (Lesson lesson : lessons) {
                    Book booking = new Book(learner, lesson);
                    availableBookings.add(booking);
                }
                bookingsGenerated = true;
            }
        }
        if (!bookingsGenerated) {
            System.out.println("There are currently no lessons booked for any user");
        }
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
        if(availableBookings.isEmpty()){
            generateBookingsForAllLearners();
        }
        List<Book> learnerBookings = new ArrayList<>();
        for (Book book : availableBookings) {
            if (book.getLearner().equals(learner)) {
                learnerBookings.add(book);
            }
        }
        return learnerBookings;
    }

}
