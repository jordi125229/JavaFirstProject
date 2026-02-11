package service;

import booking.Booking;
import booking.BookingStatus;
import money.Money;
import payment.PaymentStatus;
import pricing.HappyHoursPricing;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.*;
import resources.Device;
import resources.Resource;
import user.User;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BookingService {
    UserRepository userRepository;
    ResourceRepository resourceRepository;
    BookingRepository bookingRepository;
    PricingPolicy pricingPolicy;

    public BookingService(UserRepository userRepository,
                          ResourceRepository resourceRepository,
                          BookingRepository bookingRepository,
                          PricingPolicy pricingPolicy) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.bookingRepository = bookingRepository;
        this.pricingPolicy = pricingPolicy;
    }

    public Booking book(User u, Resource r, LocalDateTime s, LocalDateTime e) {
        if (!validation(r, s, e)) {
            throw new IllegalArgumentException("Resource isn't available in this time");
        }
        Booking b = new Booking(u, r, s, e);
        String date = s.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Money price = getPricingPolicy(b);
        b.setCalculatedPrice(price);
        b.setId("BK-<" + date + counterCreation() + ">");
        b.setStatus(BookingStatus.PENDING);
        return b;
    }

    Booking book(User u, Resource r, LocalDateTime start, int durationTime) {
        return book(u, r, start, start.plusMinutes(durationTime));
    }

    private Money getPricingPolicy(Booking b) {
        PricingPolicy pricingPolicy;
        LocalTime firstHappyHour = LocalTime.of(14, 0);
        LocalTime lastHappyHour = LocalTime.of(16, 0);
        LocalTime bookingTime = b.getStart().toLocalTime();

        if (!bookingTime.isBefore(firstHappyHour)
                && bookingTime.isBefore(lastHappyHour)) {
            pricingPolicy = new HappyHoursPricing();
        } else {
            pricingPolicy = new StandardPricing();
        }
        return pricingPolicy.price(b);
    }

    public boolean validation(Resource r, LocalDateTime s, LocalDateTime e) {
        List<Booking> bookings = bookingRepository.findByResource(r);
        return bookings.stream()
                .noneMatch(b ->
                        s.isBefore(b.getEnd()) &&
                                e.isAfter(b.getStart())
                );
    }

    public void confirm(Booking b) {
        if (b.getStatus() == BookingStatus.PENDING) {
            b.setStatus(BookingStatus.CONFIRMED);
        }
    }

    public void complete(Booking b) {
        if (b.getStatus() == BookingStatus.CONFIRMED) {
            b.setStatus(BookingStatus.COMPLETED);
        }
    }

    public void cancel(Booking b) {
        if (b.getStatus() == BookingStatus.PENDING || b.getStatus() == BookingStatus.CONFIRMED) {
            b.setStatus(BookingStatus.CANCELLED);
        }
    }

    public List<Booking> bookings() {
        return bookingRepository.findAll();
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt();
    }
}