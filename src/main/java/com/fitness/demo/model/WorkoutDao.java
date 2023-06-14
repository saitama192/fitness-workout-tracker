package com.fitness.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workoutid", nullable = false)
    private Long id;
    private LocalDateTime time;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    private List<ExerciseDao> exerciseDaos;
}
