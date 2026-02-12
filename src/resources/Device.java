package resources;

import money.Money;

public class Device extends Resource {
    private int quantity = 0;

    public Device(String name, Money customHourlyRate) {
        super(name, customHourlyRate);
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("10");
    }

    @Override
    public String describe() {
        return getName() + "; " + quantity;
    }
}
