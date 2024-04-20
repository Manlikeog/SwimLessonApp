package swimlessonapp.controllers;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;
import swimlessonapp.repository.BookingRepository;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.view.ReportView;

import java.util.List;
import java.util.Map;

public class CoachReportController {
    private final CoachRepository coachRepository;
    private final BookingRepository bookingRepository;
    private final ReportView reportView;

    public CoachReportController(CoachRepository coachRepository, ReportView reportView, BookingRepository bookingRepository) {
        this.coachRepository = coachRepository;
        this.reportView = reportView;
        this.bookingRepository = bookingRepository;
    }

    public void generateMonthlyCoachReport(int month) {
        List<Coach> coaches = coachRepository.getAllCoaches();
        displayMonthlyReportHeader(month);
        for (Coach coach : coaches) {
            List<Book> coachBookings = bookingRepository.getCoachBookingByMonth(coach,month);
            Map<Integer, Integer> ratingCounts = coachRepository.getRatingCounts(coachBookings);
            double averageRating = coachRepository.calculateAverageRating();
            reportView.displayCoachInfo(coach);
            reportView.displayAverageRating(averageRating);
            reportView.displayRatingCounts(ratingCounts);
        }
    }

    private void displayMonthlyReportHeader(int month) {
        reportView.displayMonthlyReportHeader(month);
    }
}
