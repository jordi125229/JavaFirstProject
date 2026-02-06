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
        if (customHourlyRate != null) {
            return customHourlyRate;
        }
        return baseRatePerHour();
    }

    @Override
    public String describe() {
        return name + "; " + type;
    }

    @Override
    public String toString() {
        return "Desk No: " + name + "; " + type;
    }
}
