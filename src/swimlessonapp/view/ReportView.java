package swimlessonapp.view;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ReportView {
    public void displayMonthlyReportHeader(int month) {
        System.out.println("===== Monthly Report for Month " + month + " =====");
    }

    public void displayLearnerInfo(Learner learner) {
        System.out.println("Learner: " + learner.getFirstName() + " " + learner.getLastName());
    }

    public void displayCoachInfo(Coach coach) {
        System.out.println("Coach: " + coach.getName());
    }

    public void displayAverageRating(double averageRating) {
        System.out.println("Average Rating: " + averageRating);
    }

    public void displayRatingCounts(Map<Integer, Integer> ratingCounts) {
        System.out.println("Rating Counts:");
        for (Map.Entry<Integer, Integer> entry : ratingCounts.entrySet()) {
            System.out.println("Rating " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }

    public void displayBookingsInfo(List<Book> bookings) {
        // Sort bookings by week and day

        System.out.println("Bookings:");

        int currentWeek = -1; // Initialize current week

        for (Book booking : bookings) {
            if (booking.getWeek() != currentWeek) {
                // New week encountered, display week header
                System.out.println("Booking for Week " + booking.getWeek() + ":");
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
                    booking.getLesson().getCoach().getName(),
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

        System.out.println("Booking Summary: Booked - " + booked + ", Attended - " + attended + ", Canceled - " + canceled);


    }
}
