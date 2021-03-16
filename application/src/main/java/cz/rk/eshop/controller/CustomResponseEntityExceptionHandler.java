package cz.rk.eshop.controller;

import cz.rk.eshop.exception.WatchBadValueParameterException;
import cz.rk.eshop.exception.WatchNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Custom response entity exception handler
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FIELD_SEPARATOR = ": ";
    private static final String PATH = "path";
    private static final String ERRORS = "error";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String TIMESTAMP = "timestamp";
    private static final String TYPE = "type";


    /**
     * handle own WatchNotFoundException
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(WatchNotFoundException.class)
    ResponseEntity<Object> handleWatchNotFound(WatchNotFoundException e, WebRequest request) {
        return createExceptionResponseEntity(e, HttpStatus.NOT_FOUND, request, List.of(e.getMessage()));
    }


    /**
     * handle own WatchBadValueParameterException
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(WatchBadValueParameterException.class)
    ResponseEntity<Object> handleWatchBadValueParameter(WatchBadValueParameterException e, WebRequest request) {
        return createExceptionResponseEntity(e, HttpStatus.UNPROCESSABLE_ENTITY, request, List.of(e.getMessage()));
    }


    /**
     * handle spring standard MethodArgumentNotValidException
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + FIELD_SEPARATOR + error.getDefaultMessage())
                .collect(Collectors.toList());
        return createExceptionResponseEntity(exception, status, request, validationErrors);
    }


    /**
     * create exception response entity
     * @param exception
     * @param status
     * @param request
     * @param errors
     * @return
     */
    private ResponseEntity<Object> createExceptionResponseEntity(final Exception exception, final HttpStatus status, final WebRequest request, final List<String> errors) {
        final Map<String, Object> body = new HashMap<>();
        final String path = request.getDescription(false);
        body.put(TIMESTAMP, Instant.now());
        body.put(STATUS, status.value());
        body.put(TYPE, exception.getClass().getSimpleName());
        body.put(ERRORS, errors);
        body.put(PATH, path);
        body.put(MESSAGE, status.getReasonPhrase());
        return new ResponseEntity<>(body, status);
    }

}
