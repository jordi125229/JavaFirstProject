package service;

import booking.Booking;
import payment.Billable;
import payment.Invoice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillingService implements Billable {
    @Override
    public Invoice toInvoice(Booking booking) {
        Invoice invoice = new Invoice();
        String counter = extractCounter(booking.getId());
        String date = gettingDate(booking);
        invoice.setInvoiceNumber("INV-<" + date + ">-<" + counter + ">");
        invoice.setIssueDate(LocalDateTime.now());
        invoice.setBuyer(booking.getUser());
        invoice.setTotal(booking.getCalculatedPrice());
        invoice.setItemDescription(booking.getResource().getName());
        return invoice;
    }

    private String gettingDate(Booking booking) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = booking.getStart().format(formatter);
        return date;
    }

    private String extractCounter(String bookingId) {   //sprawdzilem w chacie bo nie wiedzialem jak wyciagnac z BookingId samego countera
        Pattern pattern = Pattern.compile("BK-<.+-(\\d+)>");
        Matcher matcher = pattern.matcher(bookingId);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }
}