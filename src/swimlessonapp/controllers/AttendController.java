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

public class AttendController extends BaseController {
    public AttendController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                            CoachRepository coachRepository, UserInteraction userInteraction,
                            LessonController lessonController) {
        super(bookingRepository, learnerRepository, coachRepository,  userInteraction, lessonController);
    }

    @Override
    public void performAction() {
        Learner user = getUser();
        if (viewBookingsForLearner(user)) {
            int bookIndex = promptAndGetBookingId();
            Book selectedBook = selectBook(bookIndex);
            if (selectedBook != null && canPerformAction(selectedBook)) {
                    Lesson lesson = selectedBook.getLesson();
                    if (lessonController.checkGradeLevel(user, lesson)) {
                        provideReviewAndRating(selectedBook);
                        String message = "You have attended Lesson for Grade " + lesson.getGradeLevel() + " on " + lesson.getDay() + " at " + lesson.getTime();
                        if (lesson.getGradeLevel() > user.getCurrentGradeLevel()) {
                            user.setCurrentGradeLevel(lesson.getGradeLevel());
                            message += "\nYou have been promoted to Grade " + user.getCurrentGradeLevel();
                        }
                        printResult(true, message);
                        selectedBook.setStatus("attended");
                    } else {
                        printResult(false, "Can't attend Grade Lesson as your grade doesn't match the lesson requirements grade");
                    }
            }
            redoAction("Attend another Lesson");
        }
    }
    private void provideReviewAndRating(Book selectedBook) {
        String review = stringInput("Please provide a review for the lesson:");
        int rating;
        do {
            rating = intInput("Please provide a numerical rating (1 to 5) for the coach:");
        } while (rating > 5);

        // You can save the review and rating to the booking or any other appropriate data structure
        selectedBook.setReview(review);
        selectedBook.setRating(rating);
        coachRepository.addRatingForCoach(selectedBook.getLesson().getCoach(), rating);
    }
}