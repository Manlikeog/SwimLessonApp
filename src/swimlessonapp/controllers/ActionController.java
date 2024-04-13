package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

import java.util.List;
import java.util.Objects;

import static swimlessonapp.Config.*;

public abstract class ActionController {
    protected final BookingRepository bookingRepository;
    protected final LearnerRepository learnerRepository;
    protected final TimeTableView timeTableView;
    protected final LessonController lessonController;

    public ActionController(BookingRepository bookingRepository, TimeTableView timeTableView, LessonController lessonController, LearnerRepository learnerRepository) {
        this.bookingRepository = bookingRepository;
        this.timeTableView = timeTableView;
        this.lessonController = lessonController;
        this.learnerRepository = learnerRepository;
    }

    public abstract void performAction();

    protected Book selectBook() {
        int lessonIndex = promptAndGetBookingId();
        Book selectedBook = null;
        selectedBook = bookingRepository.getBookingById(lessonIndex);
        if(selectedBook == null){
            System.out.println("Invalid lesson index!");
           return selectBook();
        }
        return selectedBook;
    }

    protected boolean viewBookingsForLearner(Learner learner) {
        List<Book> learnerBookings = bookingRepository.getAvailableBookingsForLearner(learner);
        if (learnerBookings.isEmpty()) {
            System.out.println("No available bookings for " + learner.getFirstName() + " " + learner.getLastName() + ".");
            return false;
        } else {
            timeTableView.displayBookings(learnerBookings);
        }
        return true;
    }

    protected boolean canPerformAction(Book selectedBook) {
        String status = selectedBook.getStatus();
        if (Objects.equals(status, "attended") || Objects.equals(status, "canceled")) {
            System.out.println("Can't perform action on this lesson as it has been " + status);
            return false;
        }
        return true;
    }

    protected Lesson selectLesson() {
        return lessonController.getLessonById(promptAndGetLessonId());
    }

    protected void redoAction(String prompt, Learner user) {
        char bookAgain = '0';
        while (bookAgain != 'Y') {
            bookAgain = charInput("\nWould you like to " + prompt + " ? (Y / N / E(EXIT)");
            switch (bookAgain) {
                case 'N':
                    stringOutput("Exit....!");
                    return;
                case 'Y':
                    performAction();
                default:
                    stringOutput("Invalid choice. Please try again.");
            }
        }

    }

    protected Learner getUser() {
        Learner learner;
        boolean userExists = false;
        do {
            String firstName = stringInput("Enter first name");
            String lastName = stringInput("Enter last name");
            learner = learnerRepository.existingLearner(firstName, lastName);

            if (learner != null) {
                userExists = true;
            } else {
                stringOutput("User not found. Please try again.");
            }
        } while (!userExists);
        return learner;
    }
}

