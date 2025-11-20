package com.fitness.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class WorkoutDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workoutid", nullable = false)
    private Long id;
    private LocalDateTime time;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    private List<ExerciseDao> exerciseDaos;

    // No-args constructor (required by JPA)
    public WorkoutDao() {
    }

    // All-args constructor
    public WorkoutDao(Long id, LocalDateTime time, List<ExerciseDao> exerciseDaos) {
        this.id = id;
        this.time = time;
        this.exerciseDaos = exerciseDaos;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<ExerciseDao> getExerciseDaos() {
        return exerciseDaos;
    }

    public void setExerciseDaos(List<ExerciseDao> exerciseDaos) {
        this.exerciseDaos = exerciseDaos;
    }

    // Builder pattern for compatibility with existing code
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private LocalDateTime time;
        private List<ExerciseDao> exerciseDaos;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder time(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Builder exerciseDaos(List<ExerciseDao> exerciseDaos) {
            this.exerciseDaos = exerciseDaos;
            return this;
        }

        public WorkoutDao build() {
            return new WorkoutDao(id, time, exerciseDaos);
        }
    }
}
