package com.fitness.demo.dto;

public record ExerciseDto(
        String name,
        int reps,
        int weightMin,
        int weightMax) {
}
