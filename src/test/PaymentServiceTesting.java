package test;

import booking.Booking;
import money.Money;
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

class PaymentServiceTesting {
    public static void main(String[] args) {
        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryResourceRepository resourceRepo = new InMemoryResourceRepository();
        InMemoryBookingRepository bookingRepo = new InMemoryBookingRepository();
        PricingPolicy pricingPolicy = new StandardPricing();
        PaymentService paymentService = new PaymentService(bookingRepo);
        BookingService bookingService = new BookingService(userRepo, resourceRepo, bookingRepo, pricingPolicy);

        CompanyUser user1 = creatingCompanyUserTest();
        Desk desk = creatingDeskTest("001", Money.of("30"), DeskType.HOT);

        Booking book = bookingService.book(user1, desk, LocalDateTime.of(2026,1,4,16,30),
                LocalDateTime.of(2026,1,4,16,30).plusHours(2));
        String id = book.getId();
        bookingRepo.add(book);
        CardPayment cardPayment = createCardPaymentTest("123");
        Payment pay = paymentService.pay(id, cardPayment.getLast4());

        System.out.println(pay.toString());
        System.out.println(book);

    }
    private static CompanyUser creatingCompanyUserTest() {
        return new CompanyUser("jakub.nowak@gmail.com", "PHU Nowak", "99391009");
    }

    private static IndividualUser creatingIndividualUserTest() {
        return new IndividualUser("patryk.kowalski@wp.pl", "Patryk56");
    }

    private static Desk creatingDeskTest(String name, Money customHourlyRate, DeskType deskType) {
        return new Desk (name, customHourlyRate, deskType);
    }

    private static CardPayment createCardPaymentTest(String last4CardNumber){
        return new CardPayment(last4CardNumber);
    }
}
