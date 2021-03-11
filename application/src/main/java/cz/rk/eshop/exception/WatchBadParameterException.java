package cz.rk.eshop.exception;

/**
 * Wath bad parameter exception
 */
public class WatchBadParameterException extends RuntimeException {

    public WatchBadParameterException() {
        super("Watch contains bad value of parameter and could not be processed.");
    }
}
