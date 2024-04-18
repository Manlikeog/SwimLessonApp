package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

import static swimlessonapp.Config.printResult;
import static swimlessonapp.Config.stringOutput;

public class EditBookingController extends ActionController {
    private final TimeTableView timeTableView;
    private final LessonController lessonController;

    public EditBookingController(BookingRepository bookingRepository, TimeTableView timeTableView, LessonController lessonController, LearnerRepository learnerRepository) {
        super(bookingRepository, timeTableView, lessonController, learnerRepository, null, null);
        this.lessonController = lessonController;
        this.timeTableView = timeTableView;
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        if (viewBookingsForLearner(user)) {
            Book selectedBook = selectBook(inputBookingId());
            if(selectedBook != null){
                if (canPerformAction(selectedBook)) {
                    stringOutput("To edit booking please choose a new lesson");
                    timeTableView.printTimeTable(lessonController.getAvailableLessons());
                    Lesson selectedLesson = selectLesson();
                    if (selectedBook.getLesson() == selectedLesson) {
                        printResult(false,"Can't Edit same lesson");
                        return;
                    }
                    handleEdit(selectedBook, selectedLesson, user);
                }
            }
            redoAction("Edit another lesson");
        }
    }

    private void handleEdit(Book selectedBook, Lesson selectedLesson, Learner user) {
        if (lessonController.checkGradeLevel(user, selectedLesson)) {
            if (lessonController.addLearnerToLesson(user, selectedLesson)) {
                if (lessonController.cancelLesson(user, selectedBook.getLesson())) {
                    selectedBook.setLesson(selectedLesson);
                }
            }
        } else {
            printResult( false, "Can't attend Grade Lesson as your grade doesn't match the lesson grade");
        }
    }
}
