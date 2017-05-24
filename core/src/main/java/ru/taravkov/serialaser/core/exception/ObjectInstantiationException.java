package ru.taravkov.serialaser.core.exception;

/**
 * @author vtaravkov
 * @since 1.0
 */
public class ObjectInstantiationException extends RuntimeException {
    public ObjectInstantiationException(String message) {
        super(message);
    }

    public ObjectInstantiationException(Throwable cause) {
        super(cause);
    }

    public ObjectInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
