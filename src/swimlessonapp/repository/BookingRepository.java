package swimlessonapp.repository;

import swimlessonapp.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private static final List<Book> availableBookings = new ArrayList<>();

    private static BookingRepository instance;
    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepository();
        }
        return instance;
    }



}
