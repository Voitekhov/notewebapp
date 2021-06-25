package net.ru.voitekhov.notewebapp.exception.handler;


import net.ru.voitekhov.notewebapp.exception.BadRequestException;
import net.ru.voitekhov.notewebapp.exception.NotFoundException;
import net.ru.voitekhov.notewebapp.util.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class, BadRequestException.class})
    public ResponseEntity<?> resourceNotFoundHandling(Exception exception) {
        HttpStatus httpStatus;
        if (exception instanceof NotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ResponseBody responseBody = new ResponseBody(exception.getMessage(), exception, httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(responseBody, httpStatus);
    }
}
