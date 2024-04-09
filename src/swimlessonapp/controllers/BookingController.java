package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;

import java.util.Objects;

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
        if (manageLesson.checkGradeLevel(user, selectedLesson)) {
            manageLesson.addLearnerToLesson(user, selectedLesson);
            Book newBooking = new Book(user, selectedLesson);
            bookingRepository.addBooking(newBooking);
        } else {
            System.out.println("Can't attend Grade Lesson as your grade doesn't match the lesson grade");
        }

        // Proceed with any further actions related to the selected lesson

    }

    public void attendLesson() {
        Learner user = manageLearner.getLearner();
        if(viewBookings()) {
            Book selectedBook = selectBook();
            if (manageLesson.checkGradeLevel(user, selectedBook.getLesson())) {
                if(Objects.equals(selectedBook.getStatus(), "attended")){
                    System.out.println("You have attended this lesson");
                } else if(Objects.equals(selectedBook.getStatus(), "canceled")){
                    System.out.println("Can't cancel lesson has it has been canceled already");
                }
                    else {
                    selectedBook.setStatus("attended");
                    System.out.println("You have attended Lesson for Grade " + selectedBook.getLesson().getGradeLevel() + " on " + selectedBook.getLesson().getDay() + " at " + selectedBook.getLesson().getTime());
                    if(selectedBook.getLesson().getGradeLevel() > selectedBook.getLearner().getCurrentGradeLevel()){
                        user.setCurrentGradeLevel(selectedBook.getLesson().getGradeLevel());
                        System.out.println("You have been promoted to " + "Grade " + user.getCurrentGradeLevel());
                    }

                }

            } else {
                System.out.println("Can't attend Grade Lesson as your grade doesn't match the lesson requirements grade");
            }

        }
    }

    public void changeOrCancelBooking(){
        if(viewBookings()){
            int choice = config.intInput("""
                Select Option:
                1. Cancel Booking
                2. Change Booking
                """);
            switch (choice){
                case 1:
                    cancelBooking();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input!");
                    changeOrCancelBooking();
            }
        }
    }

    public void cancelBooking(){
        Learner user = manageLearner.getLearner();
        Book selectedBook = selectBook();
        if(Objects.equals(selectedBook.getStatus(), "attended")){
            System.out.println("This Lesson can't be canceled as it has been attended");
        } else if(Objects.equals(selectedBook.getStatus(), "canceled")){
            System.out.println("Can't cancel lesson has it has been canceled already");
        } else {
            manageLesson.cancelLesson(user, selectedBook.getLesson());
            selectedBook.setStatus("canceled");
        }
    }

    public Book selectBook(){
        printBookings();
        Book selectedBook;
        do {
            int lessonIndex = config.intInput("""
                        Select Lesson to attend above!!
                        Input Booking ID:""");
            selectedBook = bookingRepository.getBookingById(lessonIndex);
            if (selectedBook == null) {
                System.out.println("Invalid lesson index!");
            }
        } while (selectedBook == null);
        return selectedBook;
    }

    public boolean viewBookings(){
        Learner user = manageLearner.getLearner();
        if (bookingRepository.getAllBookings().isEmpty()) return bookingRepository.generateBookingForLearner(user);
        return false;
    }
    public void printBookings() {
        bookingRepository.printAvailableBookings();
    }

}
