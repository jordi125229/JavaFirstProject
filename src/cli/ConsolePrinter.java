package cli;

import booking.Booking;
import resources.Resource;
import user.User;

import java.util.List;

public class ConsolePrinter {
    public void printLine(String text) {
        System.out.println(text);
    }

    public void printUsers(List<User> userList) {
        for (User user : userList) {
            printLine(user.toString());
        }
    }

    public void printResources(List<Resource> resourceList) {
        for (Resource resource : resourceList) {
            printLine(resource.toString());
        }
    }

    public void printBookings(List<Booking> bookingList) {
        for (Booking booking : bookingList) {
            printLine(booking.toString());
        }
    }
}
