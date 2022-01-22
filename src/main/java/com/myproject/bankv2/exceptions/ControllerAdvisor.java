package com.myproject.bankv2.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("TimeStamp", LocalDateTime.now());
        result.put("Message", exception.getMessage());
        result.put("Status", "Server Error");
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleUsernameNotFoundException (UsernameNotFoundException e) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("TimeStamp", LocalDateTime.now());
        result.put("Message", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("TimeStamp", LocalDateTime.now());
        result.put("Message", e.getMessage());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ExceptionHandler(value = {InvalidUserDataException.class})
    protected ResponseEntity<Object> invalidUserDataException(InvalidUserDataException e){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("TimeStamp",LocalDateTime.now());
        result.put("Message", e.getMessage());
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("TimeStamp", LocalDateTime.now());
        result.put("Message", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CardNotFoundException.class)
    protected ResponseEntity<Object> handleBankAccountNotFound(CardNotFoundException e){
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("Time", LocalDateTime.now());
        result.put("Message",e.getMessage());
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }
}
