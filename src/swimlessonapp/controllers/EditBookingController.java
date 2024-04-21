package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.UserInteraction;

import static swimlessonapp.Config.*;
import static swimlessonapp.Config.promptAndGetLessonId;

public class EditBookingController extends BaseController {
    public EditBookingController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                                 UserInteraction userInteraction,
                                 LessonController lessonController) {
        super(bookingRepository, learnerRepository, null,  userInteraction, lessonController);
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        if (viewBookingsForLearner(user)) {
            int bookIndex = promptAndGetBookingId();
            Book selectedBook = selectBook(bookIndex);
            if (selectedBook != null && canPerformAction(selectedBook)) {
                stringOutput("To edit booking please choose a new lesson");
                userInteraction.printTimeTable(lessonController.getAvailableLessons());
                int lessonIndex = intInput(promptAndGetLessonId());
                Lesson selectedLesson = selectLesson(lessonIndex);
                if (selectedLesson != null && selectedBook.getLesson() != selectedLesson) {
                    handleEdit(selectedBook, selectedLesson, user);
                } else {
                    printResult(false, "Can't Edit same lesson");
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
            printResult(false, "Can't attend Grade Lesson as your grade doesn't match the lesson grade");
        }
    }
}
