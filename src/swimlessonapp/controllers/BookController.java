package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

import static swimlessonapp.Config.printResult;

public class BookController extends ActionController {

    private final TimeTableView timeTableView;
    private final LessonController lessonController;
    private final BookingRepository bookingRepository;

    public BookController(BookingRepository bookingRepository, TimeTableView timeTableView, LessonController lessonController, LearnerRepository learnerRepository) {
        super(bookingRepository, timeTableView, lessonController, learnerRepository, null, null);
        this.bookingRepository = bookingRepository;
        this.lessonController = lessonController;
        this.timeTableView = timeTableView;
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        timeTableView.printTimeTable(lessonController.getAvailableLessons());
        Lesson selectedLesson = selectLesson();
        if (lessonController.checkGradeLevel(user, selectedLesson)) {
            if (lessonController.addLearnerToLesson(user, selectedLesson)) {
                Book newBooking = new Book(user, selectedLesson);
                bookingRepository.addBooking(newBooking);
            }
        } else {
            printResult(false,"Can't attend Grade Lesson as your grade doesn't match the lesson grade");

        }

        redoAction("Book another Lesson");
    }


}
