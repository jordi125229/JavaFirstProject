package booking;

import money.Money;
import payment.Payment;
import payment.PaymentStatus;
import pricing.HappyHoursPricing;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import resources.Resource;
import user.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class Booking {
    private String id;
    private User user;
    private Resource resource;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
    private Money calculatedPrice;
    private Payment payment;

    public Booking(User user, Resource resource, LocalDateTime s, LocalDateTime e) {
        this.user = user;
        this.resource = resource;
        this.start = s;
        this.end = e;
    }

    public int durationMinutes() {
        return (int) Duration.between(start, end).toMinutes();
    }

    @Override
    public String toString() {
        return "Booking: " +
                "id: " + id + " " + user +
                ", " + resource +
                ", start: " + start +
                ", end: " + end +
                ", status- " + status +
                ", calculatedPrice: " + calculatedPrice +
                ", payment status" + payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Money getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(Money calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setPayment(PaymentStatus paymentStatus) {
    }

    public void confirm() {
        if (status == BookingStatus.PENDING) {
            status = BookingStatus.CONFIRMED;
        } else {
            System.out.println("Changing of booking status isn't possible!");
        }
    }

    public void complete() {
        if (status == BookingStatus.CONFIRMED) {
            status = BookingStatus.COMPLETED;
        } else {
            System.out.println("Changing of booking status isn't possible!");
        }
    }

    public void cancel() {
        if (status == BookingStatus.PENDING || status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
        } else {
            System.out.println("Changing of booking status isn't possible!");
        }
    }
}