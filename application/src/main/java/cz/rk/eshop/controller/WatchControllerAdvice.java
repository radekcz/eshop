package cz.rk.eshop.controller;

import cz.rk.eshop.exception.WatchBadParameterException;
import cz.rk.eshop.exception.WatchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class WatchControllerAdvice {

    @ResponseBody
    @ExceptionHandler(WatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String watchNotFoundHandler(WatchNotFoundException e) {
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(WatchBadParameterException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String watchBadParameterHandler(WatchBadParameterException e) {
        return e.getMessage();
    }

}