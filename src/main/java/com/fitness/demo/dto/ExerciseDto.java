package com.fitness.demo.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExerciseDto {
    private String name;
    private int reps;
    private int weightMin;
    private int weightMax;
}
