package com.fitness.demo.dto;

import com.fitness.demo.model.ExerciseDao;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutDetailsDto(
        Long id,
        LocalDateTime time,
        List<ExerciseDao> exerciseDaos) {
}
