package swimlessonapp.view;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;
import swimlessonapp.model.Learner;

import java.util.List;

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

    public void displayBookingsInfo(List<Book> bookings) {
        System.out.println("Bookings:");
        System.out.printf("%-10s%-20s%-10s%-18s%-10s%n",
                "Day", "Time", "Grade", "Coach", "Status");
        for (Book booking : bookings) {
            System.out.printf("%-10s%-20s%-10s%-18s%-10s%n",
                    booking.getLesson().getDay(),
                    booking.getLesson().getTime(),
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
