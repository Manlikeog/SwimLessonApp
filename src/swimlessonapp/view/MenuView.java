package swimlessonapp.view;

import swimlessonapp.controllers.*;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;

public class MenuView {

    private final BookingRepository bookingRepository = BookingRepository.getInstance();
    private final LearnerRepository learnerRepository =LearnerRepository.getInstance();
    private final TimeTableView timeTableView = new TimeTableView();
    private final LessonController lessonController = LessonController.getInstance();

    public void bookLesson() {
        ActionController bookLessonController = new BookController(bookingRepository, timeTableView, lessonController, learnerRepository);
        bookLessonController.performAction();
    }

    public void attendLesson() {
        AttendController attendLessonController = new AttendController(bookingRepository, timeTableView, lessonController, learnerRepository);
        attendLessonController.performAction();
    }

    public void cancelOrChangeBooking() {
        ActionController cancelBookingController = new CancelBookingController(bookingRepository, timeTableView, lessonController, learnerRepository);
        cancelBookingController.performAction();
    }

    public void editBooking() {
        ActionController editBookingController = new EditBookingController(bookingRepository, timeTableView, lessonController, learnerRepository);
        editBookingController.performAction();
    }

    public void registerUser(){
        ActionController registerController = new RegisterController(bookingRepository, timeTableView, lessonController, learnerRepository);
        registerController.performAction();

    }
}
