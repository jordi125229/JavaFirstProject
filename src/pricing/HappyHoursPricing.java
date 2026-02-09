package pricing;

import booking.Booking;
import money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HappyHoursPricing implements PricingPolicy {
    @Override
    public Money price(Booking booking) {
        Money hourlyRate = booking.getResource().hourlyRate();
        Money pricePerMinute = hourlyRate.divide(BigDecimal.valueOf(60))
                .multiply(new BigDecimal("0.7"));
        return pricePerMinute.multiply(new BigDecimal(booking.durationMinutes()));
    }
}
