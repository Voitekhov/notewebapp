package net.ru.voitekhov.notewebapp.exception;

public class NotUniquEntityException extends RuntimeException {

    public NotUniquEntityException(String message) {
        super(message);
    }

    public NotUniquEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
