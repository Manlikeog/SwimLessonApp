package swimlessonapp.controllers;

import swimlessonapp.Config;
import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.model.Lesson;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LessonRepository;
import swimlessonapp.view.TimeTableView;

import java.util.List;

public class BookingController {


    private final BookingRepository bookingRepository = BookingRepository.getInstance();
    private static final LessonController manageLesson = LessonController.getInstance();
    private static final LearnerController manageLearner = LearnerController.getInstance();
    Learner user = manageLearner.getLearner();
    static Config config = new Config();

    public void bookLesson() {

        Lesson selectedLesson = manageLesson.getLessonById();
        if (selectedLesson != null) {
            manageLesson.addLearnerToLesson(user, selectedLesson);
            Book newBooking = new Book(user, selectedLesson);
            System.out.println("You have selected the lesson: " + selectedLesson.getDay() + " " + selectedLesson.getTime());
            // Proceed with any further actions related to the selected lesson
        } else {
            System.out.println("Invalid lesson index!");
        }
    }


    public  void  attendLesson(Lesson lesson) {
        if(bookingRepository.getAllBookings().isEmpty()){
            bookingRepository.addBookingsForLearner(user);
            attendLesson(lesson);
        } else {
            if (manageLesson.checkGradeLevel(user, lesson)) {
                int lessonIndex = config.intInput(""" 
                Select Lesson to book above!!
                Input Lesson ID:""");

            }
        }
    }

}
