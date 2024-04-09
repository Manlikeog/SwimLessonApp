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

    public boolean generateBookingForLearner(Learner learner) {
        List<Lesson> lessons = lessonRepository.getListOfLessonsForLearner(learner); // Assuming you have a method in LessonRepository to get lessons for a particular learner
        if(lessons.isEmpty()){
            System.out.println("There is currently no lessons booked for this user");
            return false;
        } else {
            for (Lesson lesson : lessons) {
                Book booking = new Book(learner, lesson);
                availableBookings.add(booking);
            }
            return true;
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

    public void printAvailableBookings() {
        System.out.println("Available Bookings:");
        System.out.printf("%-12s%-15s%-10s%-20s%-20s%-10s%-10s%-10s%-10s%n",
                "BookingID", "User", "Day", "Time", "Coach", "Grade", "Status", "Review", "Rating");

        availableBookings.forEach(book -> System.out.printf("%-12s%-15s%-10s%-20s%-20s%-10s%-10s%-10s%-10s%n",
                book.getId(),
                book.getLearner().getFirstName() + " " + book.getLearner().getLastName(),
                book.getLesson().getDay(),
                book.getLesson().getTime(),
                book.getLesson().getCoach().name(),
                book.getLesson().getGradeLevel(),
                book.getStatus(),
                book.getReview(),
                book.getRating()));
    }
}
