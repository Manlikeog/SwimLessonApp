package swimlessonapp.controllers;

import org.junit.jupiter.api.Test;
import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.ReportView;
import swimlessonapp.view.UserInteraction;

import static org.junit.jupiter.api.Assertions.*;

class BaseControllerTest {
    @Test
    public void selectBook_ValidInput_ReturnsBook() {
        // Mocking necessary dependencies
        BookingRepository bookingRepository = new BookingRepository();
        UserInteraction userInteraction = new UserInteraction();
        LessonController lessonController = new LessonController();
        LearnerRepository learnerRepository = new LearnerRepository();
        CoachRepository coachRepository = new CoachRepository();
        ReportView reportView = new ReportView();

        // Creating a test instance of ActionController
        BaseControllerForTest actionController = new BaseControllerForTest(bookingRepository, userInteraction, lessonController, learnerRepository, coachRepository, reportView);

        Learner learner = new Learner("JOHN", "DOE", 'M', 8, "1234567890", 1, 1);
        Lesson lesson = new Lesson("monday", "4:00 PM - 5:00 PM", 3, new Coach("timi oguntade"), 3);

        // Adding a test booking to the repository
        Book testBook = new Book( learner, lesson );
        testBook.setId(10);

        bookingRepository.addBooking(testBook);

        // Calling selectBook() method with valid input
        Book selectedBook = actionController.selectBook(10);
        // Asserting that the returned value is not null
        assertNotNull(selectedBook);
    }

    // Concrete subclass of ActionController for testing purposes
    private static class BaseControllerForTest extends BaseController {

        public BaseControllerForTest(BookingRepository bookingRepository, UserInteraction userInteraction, LessonController lessonController, LearnerRepository learnerRepository, CoachRepository coachRepository, ReportView reportView) {
            super(bookingRepository, learnerRepository, coachRepository, reportView, userInteraction, lessonController);
        }

        @Override
        public void performAction() {
            // Override if needed for testing
        }
    }
}