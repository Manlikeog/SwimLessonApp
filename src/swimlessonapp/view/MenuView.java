package swimlessonapp.view;

import swimlessonapp.Config;
import swimlessonapp.controllers.BookingController;
import swimlessonapp.controllers.TimeTableController;


public class MenuView {

   BookingController manageBooking = new BookingController();

    public  void bookLesson(){
        manageBooking.bookLesson();
    }

    public void attendLesson(){
        manageBooking.attendLesson();
    }

    public void cancelOrChangeBooking(){
        manageBooking.cancelBooking();
    }

    public void editBooking(){
        manageBooking.editBooking();
    }
}
