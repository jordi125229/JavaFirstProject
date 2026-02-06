package pricing;

import booking.Booking;
import money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StandardPricing implements PricingPolicy {

    @Override
    public Money price(Booking booking) {
        Money hourlyRate = booking.getResource().hourlyRate();
        Money pricePerHour = hourlyRate.divide(BigDecimal.valueOf(60));
        return pricePerHour.multiply(new BigDecimal(booking.durationMinutes()));
    }
}
