package booking;

import money.Money;
import pricing.HappyHoursPricing;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import resources.Resource;
import user.User;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class Booking {
    private String id;
    private User user;
    private Resource resource;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
    private Money calculatedPrice;
//    Payment payment; //na tym etapie nie mamy tej klasy

    public Booking(String id, User user, Resource resource, LocalDateTime start,
                   LocalDateTime end,
                   BookingStatus status, Money calculatedPrice) {
        this.id = "BK-<" + start + ">-<" + counterCreation() + ">";
        this.user = user;
        this.resource = resource;
        this.start = start;
        this.end = end;
        this.status = status;
        this.calculatedPrice = calculatedPrice;
//        this.payment = payment;
    }

    public void changeStatus(){

    }

    public void confirm() {
        if (status == BookingStatus.PENDING) {
            status = BookingStatus.CONFIRMED;
        }
    }

    public void complete() {
        if (status == BookingStatus.CONFIRMED) {
            status = BookingStatus.COMPLETED;
        }
    }

    public void cancel() {
        if (status == BookingStatus.PENDING || status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
        }
    }

    public int durationMinutes(){
        return (int) Duration.between(start, end).toMinutes();
    }

    static int counterCreation(){
        Random random = new Random();
        return random.nextInt();
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
        return "Booking id: {" + id +
                "\n" + user +
                "\n" + resource +
                "\nstart - " + start +
                ", end- " + end +
                "\nstatus - " + status +
                "\ncalculatedPrice-" + calculatedPrice;
    }

    public Resource getResource() {
        return resource;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Money getCalculatedPrice() {
        return calculatedPrice;
    }
}