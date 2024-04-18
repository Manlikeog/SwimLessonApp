package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.ReportView;

import java.util.List;

import static swimlessonapp.Config.*;

public class LearnerReportController extends ActionController {

    private final LearnerRepository learnerRepository;
    private final BookingRepository bookingRepository;
    private final ReportView reportView;

    public LearnerReportController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                                   ReportView reportView) {
        super(bookingRepository, null, null, learnerRepository, null, reportView);
        this.learnerRepository = learnerRepository;
        this.bookingRepository = bookingRepository;
        this.reportView = reportView;
    }

    @Override
    public void performAction() {
        int month = intInput("Enter month number (e.g., 03 for March)");
        generateMonthlyReport(month);
    }

    private void generateMonthlyReport(int month) {
        List<Learner> learners = learnerRepository.getAllLearners();
        displayMonthlyReportHeader(month);

        for (Learner learner : learners) {
            List<Book> learnerBookings = bookingRepository.getLearnerBookingsByMonth(learner, month);
            displayLearnerInfo(learner);
            if (learnerBookings.isEmpty()) {
                printResult(false,"No bookings was made for month " + month);
            } else {
                reportView.displayBookingsInfo(learnerBookings);
                reportView.displayBookingSummary(learnerBookings);
            }
            stringOutput(" ");
        }
    }

    private void displayLearnerInfo(Learner learner) {
        reportView.displayLearnerInfo(learner);
    }
}
