package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.ReportView;
import swimlessonapp.view.UserInteraction;

import java.util.List;
import java.util.Objects;

import static swimlessonapp.Config.*;

public abstract class BaseController {
    protected final BookingRepository bookingRepository;
    protected final LearnerRepository learnerRepository;
    protected final CoachRepository coachRepository;
    protected final ReportView reportView;
    protected final UserInteraction userInteraction;
    protected final LessonController lessonController;

    public BaseController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                          CoachRepository coachRepository, ReportView reportView, UserInteraction userInteraction,
                          LessonController lessonController) {
        this.bookingRepository = bookingRepository;
        this.learnerRepository = learnerRepository;
        this.coachRepository = coachRepository;
        this.reportView = reportView;
        this.userInteraction = userInteraction;
        this.lessonController = lessonController;
    }


    public abstract void performAction();

    protected Book selectBook(int bookIndex) {
        Book selectedBook;
        selectedBook = bookingRepository.getBookingById(bookIndex);
        if (selectedBook == null) {
            printResult(false, "Invalid Booking ID");
        }
        return selectedBook;
    }

    protected boolean viewBookingsForLearner(Learner learner) {
        List<Book> learnerBookings = bookingRepository.getAvailableBookingsForLearner(learner);
        if (learnerBookings.isEmpty()) {
            printResult(false, "No available bookings for " + learner.getFirstName() + " " + learner.getLastName() + ".");
            return false;
        } else {
            userInteraction.displayBookings(learnerBookings);
        }
        return true;
    }

    protected boolean canPerformAction(Book selectedBook) {
        String status = selectedBook.getStatus();
        if (Objects.equals(status, "attended") || Objects.equals(status, "canceled")) {
            printResult(false, "Can't perform action on this lesson as it has been " + status);
            return false;
        }
        return true;
    }

    protected Lesson selectLesson(int lessonID) {
        return lessonController.getLessonById(lessonID);
    }

    protected void redoAction(String prompt) {
        char bookAgain = '0';
        while (bookAgain != 'Y') {
            bookAgain = Character.toUpperCase(charInput("\nWould you like to " + prompt + " ? (Y / N / E(EXIT)):"));
            switch (bookAgain) {
                case 'N':
                    stringOutput("Exit....!");
                    return;
                case 'Y':
                    performAction();
                default:
                    printResult(false, "Invalid choice. Please try again.");
            }
        }
    }

    protected Learner getUser() {
        Learner learner;
        boolean userExists = false;
        userInteraction.printLearnersInRow(learnerRepository.getAllLearners());
        do {
            int userID = intInput("Enter user id");
            learner = learnerRepository.existingLearner(userID);

            if (learner != null) {
                userExists = true;
                userInteraction.printLearnerInfo(learner);
            } else {
                printResult(false, "User not found. Please try again.");
            }
        } while (!userExists);
        return learner;
    }

    protected int inputBookingId() {
        return promptAndGetBookingId();
    }
}

