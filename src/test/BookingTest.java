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
import service.BookingService;
import user.CompanyUser;

import java.time.LocalDateTime;
import java.time.LocalTime;

class BookingTest {
    public static void main(String[] args) {
        CompanyUser user1 = new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");

        Desk desk = new Desk("001", null, DeskType.HOT);
        LocalDateTime start = LocalDateTime.now().withHour(18).withMinute(30).withSecond(0).withNano(0);
        LocalDateTime end = start.plusHours(1);

//        Booking booking = new Booking("id", user1, desk, start, end, BookingStatus.CONFIRMED, null, null);
//        System.out.println(booking);
//
//        booking.cancel();
//        System.out.println(booking);
//
//        Money money = calculatePrice(booking);
//        System.out.println(money);
//    }
    }
}
