package ru.taravkov.serialaser.core.exception;

/**
 * @author vtaravkov
 * @since 1.0
 */
public class DeserializationException extends RuntimeException {
    public DeserializationException(String message) {
        super(message);
    }

    public DeserializationException(Throwable cause) {
        super(cause);
    }

    public DeserializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
