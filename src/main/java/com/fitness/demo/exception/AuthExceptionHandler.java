package com.fitness.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(value = {ExerciseDetailsAlreadyExistException.class, WorkoutDoesNotExistsException.class})
    public ResponseEntity<Object> handleApiRequestException(Exception e){
        //Create payload containing exception details
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //return response entity
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
