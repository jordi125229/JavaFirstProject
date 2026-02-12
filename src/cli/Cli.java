package cli;

import booking.Booking;
import payment.CardPayment;
import payment.Invoice;
import payment.Payment;
import payment.PaymentStatus;
import pricing.PricingPolicy;
import pricing.StandardPricing;
import repository.InMemoryBookingRepository;
import repository.InMemoryResourceRepository;
import repository.InMemoryUserRepository;
import resources.Desk;
import resources.Device;
import resources.Resource;
import resources.Room;
import service.BillingService;
import service.BookingService;
import service.PaymentService;
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
    private BillingService billingService;
    private PaymentService paymentService;

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
        this.billingService = new BillingService();
        this.paymentService = new PaymentService(bookingRepository);
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
                case CANCEL -> cancelBooking();
                case CONFIRM -> confirmBooking();
                case COMPLETE -> completeBooking();
                case LIST_BOOKINGS -> bookingPrinter();
                case INVOICE -> createInvoice();
                case PAY -> createPayment();
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
        Booking booking = getBooking(start, byEmail, byName);
        bookingRepository.add(booking);
        consolePrinter.printLine("Booking created!");
    }

    private Booking getBooking(LocalDateTime start, Optional<User> byEmail, Optional<Resource> byName) {
        consolePrinter.printLine("Choose option: ");
        consolePrinter.printLine("1 - Insert ending date: ");
        consolePrinter.printLine("2 - Insert duration time (minutes): ");
        int oneOfOption = dataReader.getInt();
        Booking booking;
        if (oneOfOption == 1) {
            LocalDateTime end = dataReader.readEndDate();
            if (start.isAfter(end)) {
                throw new IllegalArgumentException();
            }
            booking = bookingService.book(byEmail.get(), byName.get(), start, end);
        } else {
            int durationTime = dataReader.getInt();
            if (durationTime <= 0) {
                throw new IllegalArgumentException();
            }
            booking = bookingService.book(byEmail.get(), byName.get(), start, durationTime);
        }
        return booking;
    }

    public Invoice createInvoice() {
        Booking booking = getBooking();
        Invoice invoice = billingService.toInvoice(booking);
        consolePrinter.printLine(invoice.toString());
        return new Invoice();
    }

    public void createPayment() {
        System.out.println("Insert card's numer:");
        String cardNumber = dataReader.getString();
        String id = getBooking().getId();
        CardPayment card = new CardPayment(cardNumber);
        paymentService.pay(id, cardNumber);
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

    public void completeBooking() {
        Booking booking = getBooking();
        bookingService.complete(booking);
        consolePrinter.printLine(booking.toString());
    }

    public void cancelBooking() {
        Booking booking = getBooking();
        bookingService.cancel(booking);
        consolePrinter.printLine(booking.toString());
    }

    public void confirmBooking() {
        Booking booking = getBooking();
        bookingService.confirm(booking);
        consolePrinter.printLine(booking.toString());
    }

    private Booking getBooking() {
        String id = dataReader.bookingFinder();
        Optional<Booking> byId = bookingRepository.findById(id);
        Booking booking = byId.get();
        return booking;
    }
}