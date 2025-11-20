package com.fitness.demo.model;

import jakarta.persistence.*;

@Entity
public class ExerciseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int reps;
    private int weightMin;
    private int weightMax;

    // No-args constructor (required by JPA)
    public ExerciseDao() {
    }

    // All-args constructor
    public ExerciseDao(Long id, String name, int reps, int weightMin, int weightMax) {
        this.id = id;
        this.name = name;
        this.reps = reps;
        this.weightMin = weightMin;
        this.weightMax = weightMax;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(int weightMin) {
        this.weightMin = weightMin;
    }

    public int getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(int weightMax) {
        this.weightMax = weightMax;
    }

    // Builder pattern for compatibility with existing code
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private int reps;
        private int weightMin;
        private int weightMax;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder reps(int reps) {
            this.reps = reps;
            return this;
        }

        public Builder weightMin(int weightMin) {
            this.weightMin = weightMin;
            return this;
        }

        public Builder weightMax(int weightMax) {
            this.weightMax = weightMax;
            return this;
        }

        public ExerciseDao build() {
            return new ExerciseDao(id, name, reps, weightMin, weightMax);
        }
    }
}
