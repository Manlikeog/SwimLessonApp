package swimlessonapp.repository;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;


import java.util.ArrayList;
import java.util.List;


public class CoachRepository {

    private static final List<Coach> listOfCoaches = new ArrayList<>();

    private static CoachRepository instance;
    public static CoachRepository  getInstance() {
        if (instance == null) {
            instance = new CoachRepository();
        }
        return instance;
    }

    static {
        listOfCoaches.add(new Coach("Linda Mabuto"));
        listOfCoaches.add(new Coach("Ojo Convenant"));
        listOfCoaches.add(new Coach("John Afolabi"));
        listOfCoaches.add(new Coach("Israel Soyombo"));
    }

    public double calculateAverageRating(Coach coach, int month) {
        int sum = 0;
        int count = 0;
        for (Book booking : coach.getBookings()) {
            if (booking.getMonth() == month && booking.getStatus().equals("attended")) {
                sum += booking.getRating();
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }
    public List<Coach> getAllCoaches() {
        return listOfCoaches;
    }
}
