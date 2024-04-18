package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

import static swimlessonapp.Config.printResult;

public class CancelBookingController extends ActionController {
    private final LessonController lessonController;

    public CancelBookingController(BookingRepository bookingRepository, TimeTableView timeTableView, LessonController lessonController, LearnerRepository learnerRepository) {
        super(bookingRepository, timeTableView, lessonController, learnerRepository, null, null);
        this.lessonController = lessonController;
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        if (viewBookingsForLearner(user)) {
            Book selectedBook = selectBook(inputBookingId());
            if(selectedBook != null){
                if (canPerformAction(selectedBook)) {
                    Lesson lesson = selectedBook.getLesson();
                    if (lessonController.cancelLesson(user, lesson)) {
                        printResult(true,"You have canceled Lesson for Grade " + lesson.getGradeLevel() + " on " + lesson.getDay() + " at " + lesson.getTime());
                        selectedBook.setStatus("canceled");
                    }
                }
            }
            redoAction("Cancel another Lesson");
        }
    }
}
