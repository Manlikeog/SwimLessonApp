package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.view.TimeTableView;

import java.util.List;
import java.util.Objects;

import static swimlessonapp.Config.getUser;
import static swimlessonapp.Config.intInput;

public class BookingController {
    BookingRepository bookingRepository = BookingRepository.getInstance();
    LessonController manageLesson = LessonController.getInstance();
    TimeTableController timeTable = new TimeTableController();
    TimeTableView view = new TimeTableView();

    public void bookLesson() {
        Learner user = getUser();
        if(timeTable.viewTimeTable()){
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
        }
    }

    public void attendLesson() {
        Learner user = getUser();
        if (viewBookingsForLearner(user)) {
            Book selectedBook = selectBook();
            if (manageLesson.checkGradeLevel(user, selectedBook.getLesson())) {
                if (Objects.equals(selectedBook.getStatus(), "attended")) {
                    System.out.println("Can't attend this lesson has it has been attended");
                } else if (Objects.equals(selectedBook.getStatus(), "canceled")) {
                    System.out.println("Can't attend this lesson has it has been canceled already");
                } else {
                    selectedBook.setStatus("attended");
                    System.out.println("You have attended Lesson for Grade " + selectedBook.getLesson().getGradeLevel() + " on " + selectedBook.getLesson().getDay() + " at " + selectedBook.getLesson().getTime());
                    if (selectedBook.getLesson().getGradeLevel() > selectedBook.getLearner().getCurrentGradeLevel()) {
                        user.setCurrentGradeLevel(selectedBook.getLesson().getGradeLevel());
                        System.out.println("You have been promoted to " + "Grade " + user.getCurrentGradeLevel());
                    }

                }

            } else {
                System.out.println("Can't attend Grade Lesson as your grade doesn't match the lesson requirements grade");
            }

        }
    }

    public void cancelBooking() {
        Learner user = getUser();

        if(viewBookingsForLearner(user)){
            Book selectedBook = selectBook();
            if (Objects.equals(selectedBook.getStatus(), "attended")) {
                System.out.println("This Lesson can't be canceled as it has been attended");
            } else if (Objects.equals(selectedBook.getStatus(), "canceled")) {
                System.out.println("Can't cancel lesson has it has been canceled already");
            } else {
                manageLesson.cancelLesson(user, selectedBook.getLesson());
                selectedBook.setStatus("canceled");
            }
        }

    }
    public Book selectBook() {
        int lessonIndex = intInput("""
                To select lesson, Input ID:""");
        Book selectedBook = bookingRepository.getBookingById(lessonIndex);
        if (selectedBook == null) {
            System.out.println("Invalid lesson index!");
            selectBook();
        }

        return selectedBook;
    }

    public boolean viewBookingsForLearner(Learner learner) {
        List<Book> learnerBookings = bookingRepository.getAvailableBookingsForLearner(learner);
        if (learnerBookings.isEmpty()) {
            System.out.println("No available bookings for " + learner.getFirstName() + " " + learner.getLastName() + ".");
            return false;
        } else {
            view. displayBookings(learnerBookings);
        }

        return true;
    }

}
