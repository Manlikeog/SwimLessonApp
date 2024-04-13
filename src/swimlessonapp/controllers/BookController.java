package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

public class BookController extends ActionController {

    public BookController(BookingRepository bookingRepository, TimeTableView timeTableView, LessonController lessonController, LearnerRepository learnerRepository) {
        super(bookingRepository, timeTableView, lessonController, learnerRepository);
    }

    @Override
    public void performAction() {
        Learner  user = getUser();
        if (timeTableView.printTimeTable(lessonController.getAvailableLessons())) {
            Lesson selectedLesson = selectLesson();
            if (lessonController.checkGradeLevel(user, selectedLesson)) {
                if (lessonController.addLearnerToLesson(user, selectedLesson)) {
                    Book newBooking = new Book(user, selectedLesson);
                    bookingRepository.addBooking(newBooking);
                }
            } else {
                System.out.println("Can't attend Grade Lesson as your grade doesn't match the lesson grade");

            }
            redoAction("Book another Lesson", user);
        }
    }


}
