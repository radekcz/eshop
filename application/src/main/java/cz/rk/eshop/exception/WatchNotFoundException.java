package cz.rk.eshop.exception;

/**
 * Watch not found exception
 */
public class WatchNotFoundException extends RuntimeException {

    public WatchNotFoundException(Long id) {
        super("Could not find watch with ID: " + id);
    }
}
