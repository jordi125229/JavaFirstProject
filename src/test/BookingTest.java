package test;

import booking.Booking;
import booking.BookingStatus;
import money.Money;
import pricing.HappyHoursPricing;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import resources.Desk;
import resources.DeskType;
import user.CompanyUser;

import java.time.LocalDateTime;
import java.time.LocalTime;

class BookingTest {
    public static void main(String[] args) {
        CompanyUser user1 = new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
        Desk desk = new Desk("001", Money.of("20"), DeskType.HOT);
        LocalDateTime start = LocalDateTime.now().withHour(18).withMinute(30).withSecond(0).withNano(0);
        LocalDateTime end = start.plusHours(1);

        Booking booking = new Booking("id", user1, desk, start, end, BookingStatus.CONFIRMED, null);
        System.out.println(booking);

        booking.cancel();
        System.out.println(booking);

        Money money = calculatePrice(booking);
        System.out.println(money);
    }

    public static Money calculatePrice (Booking b) {
        PricingPolicy pricingPolicy;
        LocalTime firstHappyHour = LocalTime.of(14, 00);
        LocalTime lastHappyHour = LocalTime.of(16, 00);
        LocalTime bookingTime = b.getStart().toLocalTime();
        if (bookingTime.isAfter(firstHappyHour) && bookingTime.isBefore(lastHappyHour)) {
            pricingPolicy = new HappyHoursPricing();
        } else {
            pricingPolicy = new StandardPricing();
        }
        return pricingPolicy.price(b);
    }
}
