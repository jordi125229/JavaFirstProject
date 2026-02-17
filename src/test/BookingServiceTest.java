package test;

import booking.Booking;
import money.Money;
import payment.Invoice;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.*;
import resources.Desk;
import resources.DeskType;
import resources.Resource;
import service.BillingService;
import service.BookingService;
import user.CompanyUser;
import user.IndividualUser;
import user.User;

import java.time.LocalDateTime;
import java.util.List;

class BookingServiceTest {
    public static void main(String[] args) {
        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryResourceRepository resourceRepo = new InMemoryResourceRepository();
        InMemoryBookingRepository bookingRepo = new InMemoryBookingRepository();
        PricingPolicy pricingPolicy = new StandardPricing();
        BookingService bookingService = new BookingService(userRepo, resourceRepo, bookingRepo, pricingPolicy);

        CompanyUser user1 = creatingCompanyUserTest();
        IndividualUser user2 = creatingIndividualUserTest();
        Desk desk = creatingDeskTest("001", Money.of("30"), DeskType.HOT);
        Desk desk1 = creatingDeskTest("001", null, DeskType.HOT);

        userRepo.add(user1);
        userRepo.add(user2);
        resourceRepo.add(desk);
        resourceRepo.add(desk1);

        Booking booking = bookingCreationTest(bookingService.book(user1, desk, LocalDateTime.of(2026, 1, 4, 16, 30),
                LocalDateTime.of(2026, 1, 4, 16, 30).plusHours(2)), bookingRepo);

        Booking booking1 = bookingCreationTest(bookingService.book(user2, desk, LocalDateTime.of(2026, 1, 4, 15, 30),
                LocalDateTime.of(2026, 1, 4, 14, 30).plusHours(2)), bookingRepo);

        changingBookingStatusTest(booking);

        List<Booking> bookings = bookingRepo.findAll();
        System.out.println(bookings);

        List<Booking> all = bookingRepo.findAll();
        System.out.println(all);

        createInvoiceTest(booking);
    }

    private static Booking bookingCreationTest(Booking bookingService, InMemoryBookingRepository bookingRepo) {
        Booking booking = bookingService;
        bookingRepo.add(booking);
        System.out.println(booking);
        return booking;
    }

    private static void changingBookingStatusTest(Booking booking) {
        booking.cancel();
        System.out.println(booking);
    }

    private static void createInvoiceTest(Booking booking) {
        BillingService bs = new BillingService();
        Invoice invoice = bs.toInvoice(booking);
        System.out.println(invoice);
    }

    private static CompanyUser creatingCompanyUserTest() {
        return new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
    }

    private static IndividualUser creatingIndividualUserTest() {
        return new IndividualUser("patryk.kowalski@wp.pl", "Patryk56");
    }

    private static LocalDateTime creatingLocalDateTimeTest() {
        LocalDateTime start = LocalDateTime.now().withHour(18).withMinute(30).withSecond(0).withNano(0);
        System.out.println("Data created!");
        return start;
    }

    private static Desk creatingDeskTest(String name, Money customHourlyRate, DeskType deskType) {
        return new Desk(name, customHourlyRate, deskType);
    }
}
