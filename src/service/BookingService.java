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

    public Booking book(User user, Resource resource, LocalDateTime start, LocalDateTime end) {
        if (!validate(resource, start, end)) {
            throw new IllegalArgumentException("Resource isn't available in this time");
        }
        Booking booking = new Booking(user, resource, start, end);
        String date = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Money price = getPricingPolicy(booking);
        booking.setCalculatedPrice(price);
        booking.setId("BK-<" + date + counterCreation() + ">");
        booking.setStatus(BookingStatus.PENDING);
        booking.setPayment(PaymentStatus.INITIATED);
        return booking;
    }

    public Booking book(User user, Resource resource, LocalDateTime start, int durationTime) {
        return book(user, resource, start, start.plusMinutes(durationTime));
    }

    private Money getPricingPolicy(Booking booking) {
        PricingPolicy pricingPolicy;
        LocalTime bookingTime = booking.getStart().toLocalTime();
        pricingPolicy = checkingPricingPolicy(bookingTime);
        return pricingPolicy.price(booking);
    }

    private PricingPolicy checkingPricingPolicy(LocalTime bookingTime) {
        PricingPolicy pricingPolicy;
        if (isHappyHour(bookingTime)) {
            pricingPolicy = new HappyHoursPricing();
        } else {
            pricingPolicy = new StandardPricing();
        }
        return pricingPolicy;
    }

    private boolean isHappyHour(LocalTime time) {
        LocalTime start = LocalTime.of(14, 0);
        LocalTime end = LocalTime.of(16, 0);
        return !time.isBefore(start) && time.isBefore(end);
    }

    public boolean validate(Resource resource, LocalDateTime start, LocalDateTime end) {
        List<Booking> bookings = bookingRepository.findByResource(resource);
        return checkingAvailability(start, end, bookings);
    }

    private boolean checkingAvailability(LocalDateTime start, LocalDateTime end, List<Booking> bookings) {
        return bookings.stream()
                .noneMatch(b -> start.isBefore(b.getEnd()) && end.isAfter(b.getStart()));
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt();
    }
}