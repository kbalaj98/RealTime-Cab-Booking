package com.mycab.booking.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
      //  response.put("status", HttpStatus.NOT_FOUND);
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(InvalidInputException ex) {
        Map<String, Object> response = new HashMap<>();
       // response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }
}
