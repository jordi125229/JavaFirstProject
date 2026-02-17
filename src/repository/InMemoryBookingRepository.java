package repository;

import booking.Booking;
import resources.Resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookingRepository implements BookingRepository {
    private List<Booking> bookings;
    private int bookingsCount;

    public InMemoryBookingRepository() {
        this.bookings = new ArrayList<>();
        this.bookingsCount = 0;
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
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

    @Override
    public List<Booking> findByResource(Resource resource) {
        return bookings.stream()
                .filter(b -> b.getResource().equals(resource))
                .toList();
    }
}
