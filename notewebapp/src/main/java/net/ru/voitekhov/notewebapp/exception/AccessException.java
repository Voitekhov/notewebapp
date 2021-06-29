package net.ru.voitekhov.notewebapp.exception;

public class AccessException extends RuntimeException {
    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
