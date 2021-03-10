package cz.rk.eshop.exception;


public class WatchNotFoundException extends RuntimeException {

    public WatchNotFoundException(Long id) {
        super("Could not find watch with ID: " + id);
    }
}
