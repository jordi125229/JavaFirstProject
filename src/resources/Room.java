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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Set<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(Set<String> equipment) {
        this.equipment = equipment;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("30");
    }

    @Override
    public String describe() {
        return getName() + "; " + seats + "; equipment: " + equipment;
    }
}
