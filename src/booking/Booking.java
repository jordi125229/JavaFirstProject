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

//    public Booking(String id, User user, Resource resource, LocalDateTime start,
//                   LocalDateTime end,
//                   BookingStatus status, Money calculatedPrice, Payment payment) {
//        this.id = id;
//        this.user = user;
//        this.resource = resource;
//        this.start = start;
//        this.end = end;
//        this.status = status;
//        this.calculatedPrice = calculatedPrice;
//        this.payment = payment;
//    }

    public Booking(User user, Resource resource, LocalDateTime s, LocalDateTime e) {
        this.user = user;
        this.resource = resource;
        this.start = s;
        this.end = e;
    }

    public int durationMinutes(){
        return (int) Duration.between(start, end).toMinutes();
    }

     Money calculatePrice(){
        PricingPolicy pricingPolicy;
        LocalTime firstHappyHour = LocalTime.of(14, 00);
        LocalTime lastHappyHour = LocalTime.of(16, 00);
        LocalTime bookingTime = start.toLocalTime();
        if (bookingTime.isAfter(firstHappyHour) && bookingTime.isBefore(lastHappyHour)) {
            pricingPolicy = new HappyHoursPricing();
        } else {
            pricingPolicy = new StandardPricing();
        }
         return pricingPolicy.price(this);
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
                ", payment=" + payment;
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
}