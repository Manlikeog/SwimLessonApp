package swimlessonapp.controllers;

import swimlessonapp.model.Coach;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.view.ReportView;

import java.util.List;
import java.util.Map;

import static swimlessonapp.Config.intInput;

public class CoachReportController extends ActionController {
    private final CoachRepository coachRepository;
    private final ReportView reportView;

    public CoachReportController(CoachRepository coachRepository, ReportView reportView) {
        super(null, null, null, null, coachRepository, reportView);
        this.coachRepository = coachRepository;
        this.reportView = reportView;
    }

    @Override
    public void performAction() {
        int month;
        do{
            month = intInput("Enter month number (e.g., 03 for March): ");
        } while (month > 13);
        generateMonthlyCoachReport(month);
    }

    private void generateMonthlyCoachReport(int month) {
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
}
