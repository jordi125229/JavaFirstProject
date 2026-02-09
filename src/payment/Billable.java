package payment;

import booking.Booking;

public interface Billable {
    Invoice toInvoice(Booking booking);
}
