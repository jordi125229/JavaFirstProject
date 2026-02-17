package resources;

import money.Money;

import java.util.Set;

public class Room extends Resource {
    private int seats;
    private Set<String> equipment;

    public Room(String name, Money customHourlyRate, int seats, Set<String> equipment) {
        super(name, customHourlyRate);
        this.seats = seats;
        this.equipment = equipment;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("30");
    }

    @Override
    public String toString() {
        return getName() + "; " + hourlyRate() + "; " + seats + "; " + equipment;
    }
}
