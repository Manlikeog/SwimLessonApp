package swimlessonapp.view;

import swimlessonapp.controllers.*;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;

import java.util.List;

public class MenuView {

    private final BookingRepository bookingRepository = BookingRepository.getInstance();
    private final LearnerRepository learnerRepository =LearnerRepository.getInstance();
    private final TimeTableView timeTableView = new TimeTableView();
    private final LessonController lessonController = LessonController.getInstance();
    private final ReportView reportView = new ReportView();
    private final CoachRepository coachRepository = CoachRepository.getInstance();

    public void bookLesson() {
        ActionController bookLessonController = new BookController(bookingRepository, timeTableView, lessonController, learnerRepository);
        bookLessonController.performAction();
    }

    public void attendLesson() {
        AttendController attendLessonController = new AttendController(lessonController, timeTableView, learnerRepository, coachRepository, bookingRepository);
        attendLessonController.performAction();
    }

    public void cancelOrChangeBooking() {
        ActionController cancelBookingController = new CancelBookingController(bookingRepository, timeTableView, lessonController, learnerRepository);
        cancelBookingController.performAction();
    }

    public void editBooking() {
        ActionController editBookingController = new EditBookingController(bookingRepository,  timeTableView, lessonController, learnerRepository);
        editBookingController.performAction();
    }

    public void registerUser(){
        ActionController registerController = new RegisterController( learnerRepository, timeTableView);
        registerController.performAction();
    }

    public  void viewTimeTable(){
        List<Lesson> weekTimeTable = lessonController.getAvailableLessons();
        timeTableView.printLessons("This week", weekTimeTable);
    }

    public void learnerReport(){
        ActionController learnerReportController = new LearnerReportController(bookingRepository, learnerRepository, reportView);
        learnerReportController.performAction();
    }

    public void coachReport(){
        ActionController coachReportController = new CoachReportController(coachRepository, reportView);
        coachReportController.performAction();
    }
}
