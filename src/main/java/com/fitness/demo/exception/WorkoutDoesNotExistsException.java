package com.fitness.demo.exception;

public class WorkoutDoesNotExistsException extends RuntimeException{
    public WorkoutDoesNotExistsException(String message){
        super(message);
    }

}
