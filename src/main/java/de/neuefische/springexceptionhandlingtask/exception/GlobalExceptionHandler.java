package de.neuefische.springexceptionhandlingtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage localExceptionHandler(NoSuchElementException exception) {
        return new ErrorMessage(exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler({Exception.class})
    public ErrorMessage localExceptionHandler(Exception exception) {
        return new ErrorMessage("Standard exception: " + exception.getMessage(), LocalDateTime.now());
    }
}
