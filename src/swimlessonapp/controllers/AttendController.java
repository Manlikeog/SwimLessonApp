package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.TimeTableView;

import java.util.Scanner;

public class AttendController extends ActionController {
    private final LessonController lessonController;

    public AttendController(LessonController lessonController,TimeTableView timeTableView, LearnerRepository learnerRepository, BookingRepository bookingRepository) {
        super(bookingRepository, timeTableView, lessonController, learnerRepository, null, null);
        this.lessonController = lessonController;
    }

    @Override
    public void performAction() {
      Learner  user = getUser();
        if (viewBookingsForLearner(user)) {
            Book selectedBook = selectBook();
            if (canPerformAction(selectedBook)) {
                Lesson lesson = selectedBook.getLesson();
                if (lessonController.checkGradeLevel(user, lesson)) {
                    System.out.println("You have attended Lesson for Grade " + lesson.getGradeLevel() + " on " + lesson.getDay() + " at " + lesson.getTime());
                    if (lesson.getGradeLevel() > user.getCurrentGradeLevel()) {
                        user.setCurrentGradeLevel(lesson.getGradeLevel());
                        System.out.println("You have been promoted to Grade " + user.getCurrentGradeLevel());
                    }
                    selectedBook.setStatus("attended");
                    provideReviewAndRating(selectedBook);
                } else {
                    System.out.println("Can't attend Grade Lesson as your grade doesn't match the lesson requirements grade");
                }
            }
            redoAction("Attend another Lesson");
        }
    }

    private void provideReviewAndRating(Book selectedBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide a review for the lesson:");
        String review = scanner.nextLine();
        int rating;
        do{
            System.out.println("Please provide a numerical rating (1 to 5) for the coach:");
          rating = scanner.nextInt();
        } while (rating > 5 );

        // You can save the review and rating to the booking or any other appropriate data structure
        selectedBook.setReview(review);
        selectedBook.setRating(rating);
    }
}