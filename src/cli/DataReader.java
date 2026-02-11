package cli;

import booking.Booking;
import money.Money;
import repository.InMemoryBookingRepository;
import repository.InMemoryResourceRepository;
import repository.InMemoryUserRepository;
import resources.*;
import service.BookingService;
import user.CompanyUser;
import user.IndividualUser;
import user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class DataReader {
    private ConsolePrinter consolePrinter;
    private BookingService bookingService;
    private Scanner sc;

    public DataReader() {
        this.consolePrinter = new ConsolePrinter();
        this.sc = new Scanner(System.in);
    }

    public void close() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public String getString() {
        return sc.nextLine();
    }

    public CompanyUser readAndCreateCompanyUser() {
        System.out.println("Insert email: ");
        String email = sc.nextLine();
        System.out.println("Insert company's name: ");
        String companyName = sc.nextLine();
        System.out.println("Insert tax ID: ");
        String taxId = sc.nextLine();
        return new CompanyUser(email, companyName, taxId);
    }

    public IndividualUser readAndCreateIndividualUser() {
        System.out.println("Insert email: ");
        String email = sc.nextLine();
        System.out.println("Insert display name: ");
        String displayName = sc.nextLine();
        return new IndividualUser(email, displayName);
    }

    public Desk readAndCreateDesk() {
        System.out.println("Insert desk's name/number: ");
        String name = sc.nextLine();
        Money customRate = getMoney();
        System.out.println("Choose desk's type: FIXED or HOT: ");
        String deskTypeInput = sc.nextLine().toUpperCase();
        DeskType deskType = DeskType.valueOf(deskTypeInput);
        return new Desk(name, customRate, deskType);
    }

    public Room readAndCreateRoom() {
        System.out.println("Insert room's number: ");
        String name = sc.nextLine();
        Money customRate = getMoney();
        System.out.println("Insert seat's count: ");
        int seatsCount = sc.nextInt();
        System.out.println("Insert equipment of room: ");
        String s = sc.nextLine();
        return new Room(name, customRate, seatsCount, Set.of(s));
    }

    public Device readAndCreateDevice() {
        System.out.println("Insert device's name:");
        String name = sc.nextLine();
        Money customRate = getMoney();
        System.out.println("Insert quantity of devices: ");
        int quantity = sc.nextInt();
        return new Device(name, customRate, quantity);
    }

    private Money getMoney() {
        System.out.println("Insert custom hourly rate or leave it: ");
        String hourlyRateInput = sc.nextLine();
        Money customRate = null;
        if (!hourlyRateInput.isBlank()) {
            customRate = Money.of(hourlyRateInput);
        }
        return customRate;
    }

    public String readEmail() {
        System.out.println("Insert user's email:");
        return sc.nextLine();
    }

    public String readResourceName() {
        System.out.println("Insert resource's name:");
        return sc.nextLine();
    }

    public LocalDateTime readStartDate() {
        System.out.println("Insert start date: (yyyy-MM-dd HH:mm)");
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String input = sc.nextLine();
        return LocalDateTime.parse(input, formatter);
    }

    public LocalDateTime readEndDate() {
        System.out.println("Insert start date: (yyyy-MM-dd HH:mm)");
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String input = sc.nextLine();
        return LocalDateTime.parse(input, formatter);
    }
}
