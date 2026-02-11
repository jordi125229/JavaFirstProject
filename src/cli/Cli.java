package cli;

import booking.Booking;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.InMemoryBookingRepository;
import repository.InMemoryResourceRepository;
import repository.InMemoryUserRepository;
import resources.Desk;
import resources.Device;
import resources.Resource;
import resources.Room;
import service.BookingService;
import user.CompanyUser;
import user.IndividualUser;
import user.User;

import java.time.LocalDateTime;
import java.util.Optional;

public class Cli {
    private DataReader dataReader;
    private ConsolePrinter consolePrinter;
    private InMemoryUserRepository userRepository;
    private InMemoryBookingRepository bookingRepository;
    private InMemoryResourceRepository resourceRepository;
    private BookingService bookingService;
    private PricingPolicy pricingPolicy;

    public Cli() {
        this.dataReader = new DataReader();
        this.consolePrinter = new ConsolePrinter();
        this.userRepository = new InMemoryUserRepository();
        this.bookingRepository = new InMemoryBookingRepository(); // tworzymy pola i konstruktor, nie przypisujemy wartosci do tych pol, robimy to w konstuktorze
        this.resourceRepository = new InMemoryResourceRepository();
        this.pricingPolicy = new StandardPricing();
        this.bookingService = new BookingService(
                userRepository,
                resourceRepository,
                bookingRepository,
                pricingPolicy
        );
    }

    public void controlLoop() {
        Options option;
        do {
            Options.printOptions();
            int insert = dataReader.getInt();
            option = Options.fromNumber(insert);

            switch (option) {
                case ADD_USER_INDIVIDUAL -> addIndividualUser();
                case ADD_USER_COMPANY -> addComapnyUser();
                case LIST_USERS -> userPriner();
                case ADD_ROOM -> addRoom();
                case ADD_DESK -> addDesk();
                case ADD_DEVICE -> addDevice();
                case LIST_RESOURCES -> resourcesPrinter();
                case BOOK -> addBooking();
                case LIST_BOOKINGS -> bookingPrinter();
                case EXIT -> System.out.println("Goodbye!");
                default -> System.out.println("Wrong option chosen");
            }
        } while (option != Options.EXIT);
    }

    public void addIndividualUser() {
        IndividualUser individualUser = dataReader.readAndCreateIndividualUser();
        userRepository.add(individualUser);
        consolePrinter.printLine("User created!");
    }

    public void addComapnyUser() {
        CompanyUser companyUser = dataReader.readAndCreateCompanyUser();
        userRepository.add(companyUser);
        consolePrinter.printLine("User created!");
    }

    public void addDesk() {
        Desk desk = dataReader.readAndCreateDesk();
        resourceRepository.add(desk);
        consolePrinter.printLine("Desk created!");
    }

    public void addRoom() {
        Room room = dataReader.readAndCreateRoom();
        resourceRepository.add(room);
        consolePrinter.printLine("Room created!");
    }

    public void addDevice() {
        Device device = dataReader.readAndCreateDevice();
        resourceRepository.add(device);
        consolePrinter.printLine("Device created!");
    }

    public void addBooking() {
        String u = dataReader.readEmail();
        String r = dataReader.readResourceName();
        Optional<User> byEmail = userRepository.findByEmail(u);
        Optional<Resource> byName = resourceRepository.findByName(r);
        LocalDateTime start = dataReader.readStartDate();
        LocalDateTime end = dataReader.readEndDate();
        if (byEmail.isEmpty() || byName.isEmpty() || start.isAfter(end)) {
            throw new IllegalArgumentException();
        }
        Booking booking = bookingService.book(byEmail.get(), byName.get(), start, end);
        bookingRepository.add(booking);
        consolePrinter.printLine("Booking created!");
    }

    public void userPriner() {
        consolePrinter.printUsers(userRepository.findAll());
    }

    public void bookingPrinter() {
        consolePrinter.printBookings(bookingRepository.findAll());
    }

    public void resourcesPrinter() {
        consolePrinter.printResources(resourceRepository.findAllResources());
    }
}