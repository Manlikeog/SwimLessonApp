package swimlessonapp.controllers;

import swimlessonapp.model.Coach;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.view.ReportView;

import java.util.List;
import java.util.Map;

public class CoachReportController {
    private final CoachRepository coachRepository;
    private final ReportView reportView;

    public CoachReportController(CoachRepository coachRepository, ReportView reportView) {
        this.coachRepository = coachRepository;
        this.reportView = reportView;
    }

    public void generateMonthlyCoachReport(int month) {
        List<Coach> coaches = coachRepository.getAllCoaches();
        displayMonthlyReportHeader(month);
        for (Coach coach : coaches) {
            Map<Integer, Integer> ratingCounts = coachRepository.getRatingCounts(coach);
            double averageRating = coachRepository.calculateAverageRating(coach);
            reportView.displayCoachInfo(coach);
            reportView.displayAverageRating(averageRating);
            reportView.displayRatingCounts(ratingCounts);
        }
    }

    private void displayMonthlyReportHeader(int month) {
        reportView.displayMonthlyReportHeader(month);
    }
}
