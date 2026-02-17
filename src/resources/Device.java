package resources;

import money.Money;

public class Device extends Resource {
    private int quantity;

    public Device(String name, Money customHourlyRate, int quantity) {
        super(name, customHourlyRate);
        this.quantity = quantity;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("10");
    }
}
