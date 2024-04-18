package swimlessonapp.view;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;

import java.util.List;
import java.util.Map;

import static swimlessonapp.Config.stringOutput;

public class ReportView {
    public void displayMonthlyReportHeader(int month) {
        stringOutput("===== Monthly Report for Month " + month + " =====");
    }

    public void displayLearnerInfo(Learner learner) {
        stringOutput("Learner: " + learner.getFirstName() + " " + learner.getLastName());
    }

    public void displayCoachInfo(Coach coach) {
        stringOutput("Coach: " + coach.name());
    }

    public void displayAverageRating(double averageRating) {
        stringOutput("Average Rating: " + averageRating);
    }

    public void displayRatingCounts(Map<Integer, Integer> ratingCounts) {
        stringOutput("Rating Counts:");
        for (Map.Entry<Integer, Integer> entry : ratingCounts.entrySet()) {
            stringOutput("Rating " + entry.getKey() + ": " + entry.getValue());
        }
        stringOutput("");
    }

    public void displayBookingsInfo(List<Book> bookings) {
        // Sort bookings by week and day

        stringOutput("Bookings:");

        int currentWeek = -1; // Initialize current week

        for (Book booking : bookings) {
            if (booking.getWeek() != currentWeek) {
                // New week encountered, display week header
                stringOutput("Booking for Week " + booking.getWeek() + ":");
                System.out.printf("%-12s%-10s%-20s%-10s%-10s%-18s%-10s%n","BookingID",
                        "Day", "Time", "Week/Month", "Grade", "Coach", "Status");
                // Reset currentWeek to the new week
                currentWeek = booking.getWeek();
            }

            // Display booking details
            System.out.printf("%-12s%-10s%-20s%-10s%-10s%-18s%-10s%n",
                    booking.getId(),
                    booking.getLesson().getDay(),
                    booking.getLesson().getTime(),
                    booking.getWeek() + "/" + booking.getMonth(),
                    booking.getLesson().getGradeLevel(),
                    booking.getLesson().getCoach().name(),
                    booking.getStatus());

        }
    }

    public void displayBookingSummary(List<Book> bookings) {
        int booked = 0;
        int attended = 0;
        int canceled = 0;

        for (Book booking : bookings) {
            switch (booking.getStatus()) {
                case "booked":
                    booked++;
                    break;
                case "attended":
                    attended++;
                    break;
                case "canceled":
                    canceled++;
                    break;
            }
        }
        stringOutput("Booking Summary: Booked - " + booked + ", Attended - " + attended + ", Canceled - " + canceled);
    }
}
