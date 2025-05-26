package ee.lostpearls.controller;

import ee.lostpearls.infrastructure.exception.DuplicateLocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateLocationException.class)
    public ResponseEntity<String> handleDuplicateLocation(DuplicateLocationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
