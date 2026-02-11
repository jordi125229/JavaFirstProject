package test;

import booking.Booking;
import money.Money;
import payment.Invoice;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.*;
import resources.Desk;
import resources.DeskType;
import service.BillingService;
import service.BookingService;
import user.CompanyUser;
import user.IndividualUser;

import java.time.LocalDateTime;
import java.util.List;

class BookingServiceTest {
    public static void main(String[] args) {
        CompanyUser user1 = new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
        IndividualUser user2 = new IndividualUser("patryk.kowalski@wp.pl", "Patryk56");
        Desk desk = new Desk("001", null, DeskType.HOT);
        Desk desk1 = new Desk("001", null, DeskType.HOT);

        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryResourceRepository resourceRepo = new InMemoryResourceRepository();
        InMemoryBookingRepository bookingRepo = new InMemoryBookingRepository();
        PricingPolicy pricingPolicy = new StandardPricing();

        userRepo.add(user1);
        userRepo.add(user2);
        resourceRepo.add(desk);
        resourceRepo.add(desk1);
//        BookingService bookingService = new BookingService();
//
//        Booking book = bookingService.book(user1, desk, LocalDateTime.of(2026,1,4,16,30),
//                LocalDateTime.of(2026,1,4,16,30).plusHours(2));
//        Booking book2 = bookingService.book(user2, desk, LocalDateTime.of(2026,1,4,15,30),
//                LocalDateTime.of(2026,1,4,14,30).plusHours(2));
//
//        System.out.println(book);
//        bookingService.cancel(book);
////        System.out.println(book);
////        bookingService.calculatePrice(book);
//        System.out.println(book);
//
//        System.out.println(book2);
//
//        List<Booking> bookings = bookingService.bookings();
//        System.out.println(bookings);

//        List<Booking> all = bookingRepo.findAll();
//        System.out.println(all);
//
//        BillingService bs = new BillingService();
//        Invoice invoice = bs.toInvoice(book);
//        System.out.println(invoice);
    }
}
