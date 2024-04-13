package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

public class EditBookingController extends ActionController {

    public EditBookingController(BookingRepository bookingRepository, TimeTableView timeTableView, LessonController lessonController, LearnerRepository learnerRepository) {
        super(bookingRepository, timeTableView, lessonController, learnerRepository);
    }

    @Override
    public void performAction() {
        Learner  user = getUser();
        if (viewBookingsForLearner(user)) {
            Book selectedBook = selectBook();
            if (canPerformAction(selectedBook)) {
                System.out.println("To edit booking please choose a new lesson");
                if (timeTableView.printTimeTable(lessonController.getAvailableLessons())) {
                    Lesson selectedLesson = selectLesson();
                    if(selectedBook.getLesson() == selectedLesson){
                        System.out.println("Can't Edit same lesson");
                        return;
                    }
                    handleEdit(selectedBook, selectedLesson, user);
                }
            }
            redoAction("Edit another lesson", user);
        }
    }

    private void handleEdit(Book selectedBook, Lesson selectedLesson, Learner user) {
        if (lessonController.checkGradeLevel(user, selectedLesson)) {
            if(lessonController.addLearnerToLesson(user, selectedLesson)){
                if (lessonController.cancelLesson(user, selectedBook.getLesson())) {
                    selectedBook.setLesson(selectedLesson);
                }
            }
        } else {
            System.out.println("Can't attend Grade Lesson as your grade doesn't match the lesson grade");
        }
    }
}
