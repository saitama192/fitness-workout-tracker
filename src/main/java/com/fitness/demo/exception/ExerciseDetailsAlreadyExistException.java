package com.fitness.demo.exception;

public class ExerciseDetailsAlreadyExistException extends RuntimeException{
    public ExerciseDetailsAlreadyExistException(String message) {
        super(message);
    }


    public ExerciseDetailsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

