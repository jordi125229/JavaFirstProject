package service;

import booking.Booking;
import payment.CardPayment;
import payment.Payment;
import payment.PaymentStatus;
import repository.InMemoryBookingRepository;

import java.awt.print.Book;
import java.util.Optional;

public class PaymentService {
    private final InMemoryBookingRepository bookingRepository;

    public PaymentService(InMemoryBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Payment pay(String bookingId, String last4) {
        Optional<Booking> byId = bookingRepository.findById(bookingId);
        Booking booking = byId.get();
        CardPayment cardPayment = new CardPayment(last4);
        cardPayment.capture();
        booking.setPayment(cardPayment);
        return cardPayment;
    }
}