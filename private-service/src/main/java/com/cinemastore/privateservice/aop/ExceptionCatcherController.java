package com.cinemastore.privateservice.aop;

import com.cinemastore.privateservice.exception.NoSuchContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionCatcherController {

    @ExceptionHandler({NoSuchContentException.class})
    public final ResponseEntity<String> noSuchMediaCatcher(NoSuchContentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
