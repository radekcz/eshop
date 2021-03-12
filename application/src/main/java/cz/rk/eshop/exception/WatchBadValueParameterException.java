package cz.rk.eshop.exception;

/**
 * Wath bad parameter exception
 */
public class WatchBadValueParameterException extends RuntimeException {

    public WatchBadValueParameterException() {
        super("Watch contains bad value of parameter and could not be processed.");
    }
}
