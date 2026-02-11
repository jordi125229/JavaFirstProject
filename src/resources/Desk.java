package resources;

import money.Money;

public class Desk extends Resource {
    private DeskType type;

    public Desk(String name, Money customHourlyRate, DeskType type) {
        super(name, customHourlyRate);
        this.type = type;
    }

    public DeskType getType() {
        return type;
    }

    public void setType(DeskType type) {
        this.type = type;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("15");
    }

    @Override
    public String describe() {
        return getName() + "; " + type + "; " + getCustomHourlyRate();  //po co ten kod ?
    }

    @Override
    public String toString() {
        return getName() + "; " + type + "; " + hourlyRate();
    }
}
