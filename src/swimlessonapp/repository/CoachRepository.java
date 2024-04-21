package swimlessonapp.repository;

import swimlessonapp.model.Book;
import swimlessonapp.model.Coach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoachRepository {

    private static final List<Coach> listOfCoaches = new ArrayList<>();
    private static final List<Integer> coachRatings =  new ArrayList<>();

    private static CoachRepository instance;

    public static CoachRepository getInstance() {
        if (instance == null) {
            instance = new CoachRepository();
        }
        return instance;
    }

    static {
        listOfCoaches.add(new Coach("Linda Maputo"));
        listOfCoaches.add(new Coach("Ojo Convenient"));
        listOfCoaches.add(new Coach("John syllabi"));
        listOfCoaches.add(new Coach("Israel Bosomy"));
    }

    public double calculateAverageRating() {
        List<Integer> ratings = coachRatings;
        if (ratings.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        int count = 0;
        for (Integer rating : ratings) {
            sum += rating;
            count++;
        }
        return (double) sum / count;
    }

    public Map<Integer, Integer> getRatingCounts(List<Book> bookings) {
        Map<Integer, Integer> ratingCounts = new HashMap<>();
        for (Book book : bookings){
            if(book.getRating() != 0){
                coachRatings.add(book.getRating());
            }
        }
        for (Integer rating : coachRatings) {
                ratingCounts.put(rating, ratingCounts.getOrDefault(rating, 0) + 1);
        }
        return ratingCounts;
    }

    public List<Coach> getAllCoaches() {
        return listOfCoaches;
    }

}
