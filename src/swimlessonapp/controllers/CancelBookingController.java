package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.ReportView;
import swimlessonapp.view.UserInteraction;

import static swimlessonapp.Config.printResult;
import static swimlessonapp.Config.promptAndGetBookingId;

public class CancelBookingController extends BaseController {
    public CancelBookingController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                                  UserInteraction userInteraction,
                                   LessonController lessonController) {
        super(bookingRepository, learnerRepository,  null, userInteraction, lessonController);
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        if (viewBookingsForLearner(user)) {
            int bookIndex = promptAndGetBookingId();
            Book selectedBook = selectBook(bookIndex);
            if(selectedBook != null && canPerformAction(selectedBook)){
                    Lesson lesson = selectedBook.getLesson();
                    if (lessonController.cancelLesson(user, lesson)) {
                        printResult(true,"You have canceled Lesson for Grade " + lesson.getGradeLevel() + " on " + lesson.getDay() + " at " + lesson.getTime());
                        selectedBook.setStatus("canceled");
                    }
            }
            redoAction("Cancel another Lesson");
        }
    }
}
