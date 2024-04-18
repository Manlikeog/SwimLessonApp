package swimlessonapp.view;

import swimlessonapp.controllers.*;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;

import java.util.List;

import static swimlessonapp.Config.intInput;

public class MenuView {

    private final BookingRepository bookingRepository = BookingRepository.getInstance();
    private final LearnerRepository learnerRepository =LearnerRepository.getInstance();
    private final UserInteraction userInteraction = new UserInteraction();
    private final LessonController lessonController = new  LessonController();
    private final ReportView reportView = new ReportView();
    private final CoachRepository coachRepository = CoachRepository.getInstance();

    public void bookLesson() {
        BaseController bookLessonController = new BookController(bookingRepository,learnerRepository,  coachRepository,reportView, userInteraction,   lessonController);
        bookLessonController.performAction();
    }

    public void attendLesson() {
        AttendController attendLessonController = new AttendController(bookingRepository,learnerRepository,  coachRepository,reportView, userInteraction,   lessonController);
        attendLessonController.performAction();
    }

    public void cancelOrChangeBooking() {
        BaseController cancelBookingController = new CancelBookingController(bookingRepository,learnerRepository,  coachRepository,reportView, userInteraction,   lessonController);
        cancelBookingController.performAction();
    }

    public void editBooking() {
        BaseController editBookingController = new EditBookingController(bookingRepository,learnerRepository,  coachRepository,reportView, userInteraction,   lessonController);
        editBookingController.performAction();
    }

    public void registerUser(){
        BaseController registerController = new RegisterController( learnerRepository, userInteraction);
        registerController.performAction();
    }

    public  void viewTimeTable(){
        List<Lesson> weekTimeTable = lessonController.getAvailableLessons();
        userInteraction.printLessons("This week", weekTimeTable);
    }

    public void learnerReport(){
        int month = intInput("Enter month number (e.g., 03 for March)");
        LearnerReportController learnerReportController = new LearnerReportController(bookingRepository, learnerRepository, reportView);
        learnerReportController.generateMonthlyReport(month);
    }

    public void coachReport(){
        int month = intInput("Enter month number (e.g., 03 for March)");
        CoachReportController coachReportController = new CoachReportController(coachRepository, reportView);
        coachReportController.generateMonthlyCoachReport(month);
    }
}
