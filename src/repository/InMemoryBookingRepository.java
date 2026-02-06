package repository;

import booking.Booking;

import java.util.List;
import java.util.Optional;

public class InMemoryBookingRepository implements BookingRepository {
    private List<Booking> bookings;
    private int bookingsCount = 0;

    @Override
    public void add(Booking b) {
        bookings.add(b);
        bookingsCount++;
    }

    @Override
    public Optional<Booking> findById(String id) {
        for (Booking booking : bookings) {
            if (booking.getId().equalsIgnoreCase(id)) {
                return Optional.of(booking);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Booking> findAll() {
        if (bookingsCount == 0) {
            System.out.println("No bookings");
        }
        return bookings;
    }
}
