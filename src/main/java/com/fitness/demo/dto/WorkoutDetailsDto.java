package com.fitness.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkoutDetailsDto {
    int Duration;
    long UserId;

}
