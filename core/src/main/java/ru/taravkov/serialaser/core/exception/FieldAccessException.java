package ru.taravkov.serialaser.core.exception;

/**
 * @author vtaravkov
 * @since 1.0
 */
public class FieldAccessException extends RuntimeException {
    public FieldAccessException(String message) {
        super(message);
    }

    public FieldAccessException(Throwable cause) {
        super(cause);
    }

    public FieldAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
