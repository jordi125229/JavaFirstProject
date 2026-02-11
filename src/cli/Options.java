package cli;

public enum Options {
    ADD_USER_INDIVIDUAL(1, "Adding individual user"),
    ADD_USER_COMPANY(2, "Adding company user"),
    LIST_USERS(3, "Printing user's list"),
    ADD_ROOM(4, "Adding room"),
    ADD_DESK(5, "Adding desk"),
    ADD_DEVICE(6, "Adding device"),
    LIST_RESOURCES(7, "List of resources"),
    BOOK(8, "Book"),
    CONFIRM(9, "Confirmation"),
    CANCEL(10, "Cancelation"),
    LIST_BOOKINGS(11, "List of bookings"),
    EXIT(12, "Closing app");

    private final int optionNumber;
    private final String description;

    Options(int optionNumber, String description) {
        this.optionNumber = optionNumber;
        this.description = description;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public String getDescription() {
        return description;
    }

    public static Options fromNumber(int number){
        for (Options opt : Options.values()) {
            if (opt.optionNumber == number) {
                return opt;
            }
        }
        return null;
    }

    public static void printOptions(){
        System.out.println("Choose option by using numbers: ");
        Options[] values = Options.values();
        for (Options value : values) {
            System.out.println(value.optionNumber + "- " +value + "- " + value.description);
        }
    }
}
