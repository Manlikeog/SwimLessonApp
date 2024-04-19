package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.ReportView;
import swimlessonapp.view.UserInteraction;

import static swimlessonapp.Config.*;

public class BookController extends BaseController {
    public BookController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                          UserInteraction userInteraction,
                          LessonController lessonController) {
        super(bookingRepository, learnerRepository,  null, userInteraction, lessonController);
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        userInteraction.printTimeTable(lessonController.getAvailableLessons());
        int lessonIndex = intInput(promptAndGetLessonId());
        Lesson selectedLesson = selectLesson(lessonIndex);
        if(selectedLesson != null){
            if (lessonController.checkGradeLevel(user, selectedLesson)) {
                if (lessonController.addLearnerToLesson(user, selectedLesson)) {
                    Book newBooking = new Book(user, selectedLesson);
                    bookingRepository.addBooking(newBooking);
                }
            } else {
                printResult(false,"Can't attend Grade Lesson as your grade doesn't match the lesson grade");

            }
        }
        redoAction("Book another Lesson");
    }


}
