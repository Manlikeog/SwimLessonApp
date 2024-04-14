package swimlessonapp.model;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private final String name;
    private List<Book> bookings;

    public Coach(String name) {
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBookings() {
        return bookings;
    }

    public void setBookings(List<Book> bookings) {
        this.bookings = bookings;
    }

    }
