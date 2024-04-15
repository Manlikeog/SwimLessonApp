package swimlessonapp.controllers;

import swimlessonapp.model.Coach;
import swimlessonapp.repository.CoachRepository;
import swimlessonapp.view.ReportView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter month number (e.g., 03 for March): ");
        int month = scanner.nextInt();
        generateMonthlyCoachReport(month);
    }

    private void generateMonthlyCoachReport(int month) {
        List<Coach> coaches = coachRepository.getAllCoaches();
        displayMonthlyReportHeader(month);
        for (Coach coach : coaches) {
            Map<Integer, Integer> ratingCounts = coachRepository.getRatingCounts(coach, month);
            double averageRating = coachRepository.calculateAverageRating(coach, month);
            reportView.displayCoachInfo(coach);
            reportView.displayAverageRating(averageRating);
            reportView.displayRatingCounts(ratingCounts);
        }
    }
}
