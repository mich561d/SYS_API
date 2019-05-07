package exceptions;

public class BookingException extends Exception {

    public BookingException(String message) {
        super(message);
    }

    public BookingException() {
        super("The Booking didn't go through");
    }
}
