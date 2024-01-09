package de.neuefische.springexceptionhandlingtask;

import de.neuefische.springexceptionhandlingtask.exception.ErrorMessage;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ErrorMessage localExceptionHandler(IllegalArgumentException exception) {
        return new ErrorMessage(exception.getMessage(), LocalDateTime.now());
    }

    @GetMapping("/null")
    public void getNullPointerException() {
        String nullString = null;
        char willNeverExist = nullString.charAt(1);
        System.out.println(willNeverExist);
    }

    @GetMapping("/number")
    public int getNumberOfCars() {
        return 50 / 0;
    }
}
