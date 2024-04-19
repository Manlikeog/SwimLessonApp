package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Learner;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.LearnerRepository;
import swimlessonapp.view.ReportView;

import java.util.List;

import static swimlessonapp.Config.printResult;
import static swimlessonapp.Config.stringOutput;

public class LearnerReportController {
    private final BookingRepository bookingRepository;
    private final LearnerRepository learnerRepository;
    private final ReportView reportView;

    public LearnerReportController(BookingRepository bookingRepository, LearnerRepository learnerRepository,
                                   ReportView reportView) {
        this.bookingRepository = bookingRepository;
        this.learnerRepository = learnerRepository;
        this.reportView = reportView;
    }

    public void generateMonthlyReport(int month) {
        List<Learner> learners = learnerRepository.getAllLearners();
        displayMonthlyReportHeader(month);
        for (Learner learner : learners) {
            List<Book> learnerBookings = bookingRepository.getLearnerBookingsByMonth(learner, month);
            displayLearnerInfo(learner);
            if (learnerBookings.isEmpty()) {
                printResult(false, "No bookings were made for month " + month);
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

    private void displayMonthlyReportHeader(int month) {
        reportView.displayMonthlyReportHeader(month);
    }
}
