package com.fitness.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExerciseDetailsDto {
    private String name;
    private String primaryMuscle;
    private String secondaryMuscle;
}
