package exceptions;

/**
 *
 * @author lam@cphbusiness.dk
 */
public class CarException extends Exception {

    public CarException(String message) {
        super(message);
    }

    public CarException() {
        super("No results found with current data");
    }
}
