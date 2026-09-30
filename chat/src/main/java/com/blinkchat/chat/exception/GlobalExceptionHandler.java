package com.blinkchat.chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorResponseDto> handleRateLimitExceeded(RateLimitExceededException e){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponseDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        Map<String,String> errorDetails = new HashMap<>();
        String message = "Invalid expiry preset.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errorDetails = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorDetails.put(error.getField(),error.getDefaultMessage());
        });

        String message = "Invalid arguments. Please validate input.";
        ErrorResponseDto response = new ErrorResponseDto(message,errorDetails);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        ErrorResponseDto response = new ErrorResponseDto(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MaxCapacityOfLobbyReachedException.class)
    public ResponseEntity<ErrorResponseDto> handleMaxCapacityOfLobbyReachedException(MaxCapacityOfLobbyReachedException e){
        String message = e.getMessage();
        ErrorResponseDto response = new ErrorResponseDto(message);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }
}
