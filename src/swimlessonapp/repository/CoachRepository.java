package swimlessonapp.repository;

import swimlessonapp.model.Coach;

import java.util.ArrayList;
import java.util.List;

public class CoachRepository {

    private static final List<Coach> listOfCoaches = new ArrayList<>();

    static {
        listOfCoaches.add(new Coach("Linda Mabuto"));
        listOfCoaches.add(new Coach("Ojo Convenant"));
        listOfCoaches.add(new Coach("John Afolabi"));
        listOfCoaches.add(new Coach("Israel Soyombo"));
    }


    public List<Coach> getAllCoaches() {
        return listOfCoaches;
    }
}
