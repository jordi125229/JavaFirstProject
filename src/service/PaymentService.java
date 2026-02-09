package service;

import booking.Booking;
import payment.CardPayment;
import payment.Payment;
import payment.PaymentStatus;
import repository.InMemoryBookingRepository;

import java.awt.print.Book;

public class PaymentService {
    private InMemoryBookingRepository repository;

    public Payment pay(String bookingId, String last4) {
        Booking b = repository.findById(bookingId).orElseThrow();
        CardPayment cardPayment = new CardPayment(b.getCalculatedPrice(), bookingId, PaymentStatus.INITIATED, last4);

        cardPayment.capture();
        b.setPayment(cardPayment);
        return cardPayment;
    }
}
