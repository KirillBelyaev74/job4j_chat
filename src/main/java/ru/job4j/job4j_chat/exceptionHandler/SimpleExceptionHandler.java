package ru.job4j.job4j_chat.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.job4j_chat.model.ResponseException;

@ControllerAdvice
public class SimpleExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ResponseException> catchNullPointer(NullPointerException exception) {
        return new ResponseEntity<>(new ResponseException(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ResponseException> catchIllegalArgument(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ResponseException(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
