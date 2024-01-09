package de.neuefische.springexceptionhandlingtask.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        String message,
        LocalDateTime timestamp
) {
}
