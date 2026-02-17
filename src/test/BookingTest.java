package test;

import booking.Booking;
import booking.BookingStatus;
import money.Money;
import payment.Payment;
import pricing.HappyHoursPricing;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.*;
import resources.Desk;
import resources.DeskType;
import resources.Resource;
import service.BookingService;
import user.CompanyUser;
import user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

class BookingTest {
    public static void main(String[] args) {
        CompanyUser user1 = creatingUserTest();
        Desk desk = creatingResourceTest();
        LocalDateTime start = creatingLocalDateTimeTest();
        LocalDateTime end = start.plusHours(1);

        Booking booking = createBookingTest(user1, desk, start, end);
        System.out.println(booking);

        booking.cancel();
        System.out.println(booking);

        settingStatusOfBookingTest(booking);
        System.out.println(booking);

//        Money money = calculatePrice(booking);
//        System.out.println(money);
    }

    private static void settingStatusOfBookingTest(Booking booking) {
        booking.setStatus(BookingStatus.CONFIRMED);
    }

    private static LocalDateTime creatingLocalDateTimeTest() {
        LocalDateTime start = LocalDateTime.now().withHour(18).withMinute(30).withSecond(0).withNano(0);
        System.out.println("Data created!");
        return start;
    }

    private static CompanyUser creatingUserTest() {
        System.out.println("User created!");
        return new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
    }

    private static Desk creatingResourceTest() {
        System.out.println("Resource created!");
        return new Desk("001", null, DeskType.HOT);
    }

    private static Booking createBookingTest(User user, Resource resource, LocalDateTime start, LocalDateTime end) {
        System.out.println("Booking created!");
        return new Booking(user, resource, start, end);
    }
}
