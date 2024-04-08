package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;

public class BookingController {
    BookingRepository bookingRepository = BookingRepository.getInstance();
    LessonController manageLesson = LessonController.getInstance();
    LearnerController manageLearner = LearnerController.getInstance();

    static Config config = new Config();

    public void bookLesson() {
        Learner user = manageLearner.getLearner();
        Lesson selectedLesson = manageLesson.getLessonById(""" 
                Select Lesson to book above!!
                Input Lesson ID:""");

        manageLesson.addLearnerToLesson(user, selectedLesson);
//        Book newBooking = new Book(user, selectedLesson);
        System.out.println(user.getFirstName());
        System.out.println("You have selected the lesson: " + selectedLesson.getDay() + " " + selectedLesson.getTime());
        // Proceed with any further actions related to the selected lesson

    }

    public void attendLesson() {
        Learner user = manageLearner.getLearner();
        if (bookingRepository.getAllBookings().isEmpty()) {
            bookingRepository.addBookingsForLearner(user);
            attendLesson();
        } else {viewBookings();
            int lessonIndex = config.intInput("""
                    Select Lesson to attend above!!
                    Input Booking ID:""");
            Book selectedBook = bookingRepository.getBookingById(lessonIndex);
            if (selectedBook == null) {
                System.out.println("Invalid lesson index!");
                attendLesson();
            } else {
                if (manageLesson.checkGradeLevel(user, selectedBook.getLesson())) {
                    selectedBook.setStatus("attended");
                }
            }
        }
    }

    public void viewBookings() {
        bookingRepository.printAvailableBookings();
    }

}
