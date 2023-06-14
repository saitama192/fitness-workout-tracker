package com.fitness.demo.dto;

import com.fitness.demo.model.ExerciseDao;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkoutDetailsDto {
    private Long id;
    private LocalDateTime time;
    private List<ExerciseDao> exerciseDaos;

}
