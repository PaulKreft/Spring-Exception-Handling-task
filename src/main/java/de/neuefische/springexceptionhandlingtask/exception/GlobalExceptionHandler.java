package de.neuefische.springexceptionhandlingtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalExceptionHandler {

    @ExceptionHandler({ NoSuchElementException.class })
    public ErrorMessage localExceptionHandler(NoSuchElementException exception) {
        return new ErrorMessage(exception.getMessage(), LocalDateTime.now());
    }
}
