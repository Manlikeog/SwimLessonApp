package swimlessonapp.repository;

import swimlessonapp.model.Coach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoachRepository {

    private static final List<Coach> listOfCoaches = new ArrayList<>();
    private static final Map<Coach, List<Integer>> coachRatings = new HashMap<>();

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

    public double calculateAverageRating(Coach coach) {
        List<Integer> ratings = coachRatings.getOrDefault(coach, new ArrayList<>());
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

    public Map<Integer, Integer> getRatingCounts(Coach coach) {
        List<Integer> ratings = coachRatings.getOrDefault(coach, new ArrayList<>());
        Map<Integer, Integer> ratingCounts = new HashMap<>();
        for (Integer rating : ratings) {
            ratingCounts.put(rating, ratingCounts.getOrDefault(rating, 0) + 1);
        }
        return ratingCounts;
    }

    public List<Coach> getAllCoaches() {
        return listOfCoaches;
    }

    // Method to add a rating for a coach
    public void addRatingForCoach(Coach coach, int rating) {
        List<Integer> ratings = coachRatings.getOrDefault(coach, new ArrayList<>());
        ratings.add(rating);
        coachRatings.put(coach, ratings);
    }
}
