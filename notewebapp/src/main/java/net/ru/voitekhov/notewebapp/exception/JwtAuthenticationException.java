package net.ru.voitekhov.notewebapp.exception;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String s, HttpStatus unauthorized) {
    }
}
