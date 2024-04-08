package swimlessonapp.view;

import swimlessonapp.Config;
import swimlessonapp.controllers.BookingController;
import swimlessonapp.controllers.TimeTableController;


public class MenuView {
    Config config = new Config();
   TimeTableController timeTableController = new TimeTableController();
    final TimeTableView timeTable = new TimeTableView();
   BookingController manageBooking = new BookingController();

    public  void bookLesson(){
        timeTable.viewTimeTable();
        manageBooking.bookLesson();
    }

    public void attendLesson(){


        manageBooking.attendLesson();
        manageBooking.viewBookings();
    }


}
