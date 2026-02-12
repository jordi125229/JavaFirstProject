package test;

import booking.Booking;
import payment.CardPayment;
import payment.Payment;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.InMemoryBookingRepository;
import repository.InMemoryResourceRepository;
import repository.InMemoryUserRepository;
import resources.Desk;
import resources.DeskType;
import service.BookingService;
import service.PaymentService;
import user.CompanyUser;
import user.IndividualUser;

import java.time.LocalDateTime;

class PaymentTesting {
    public static void main(String[] args) {
        CompanyUser user1 = new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
        IndividualUser user2 = new IndividualUser("patryk.kowalski@wp.pl", "Patryk56");
        Desk desk = new Desk("001", null, DeskType.HOT);
        Desk desk1 = new Desk("001", null, DeskType.HOT);

        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryResourceRepository resourceRepo = new InMemoryResourceRepository();
        InMemoryBookingRepository bookingRepo = new InMemoryBookingRepository();
        PricingPolicy pricingPolicy = new StandardPricing();
        PaymentService paymentService = new PaymentService(bookingRepo);

        BookingService bookingService = new BookingService(userRepo, resourceRepo, bookingRepo, pricingPolicy);

        Booking book = bookingService.book(user1, desk, LocalDateTime.of(2026,1,4,16,30),
                LocalDateTime.of(2026,1,4,16,30).plusHours(2));
        String id = book.getId();
        bookingRepo.add(book);

        CardPayment cardPayment = new CardPayment("1234");

        Payment pay = paymentService.pay(id, cardPayment.getLast4());
        System.out.println(pay.toString());

        System.out.println(book);

    }
}
