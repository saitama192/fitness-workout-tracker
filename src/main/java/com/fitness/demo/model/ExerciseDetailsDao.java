package com.fitness.demo.model;

import jakarta.persistence.*;

@Entity
public class ExerciseDetailsDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String primaryMuscle;
    private String secondaryMuscle;

    // No-args constructor (required by JPA)
    public ExerciseDetailsDao() {
    }

    // All-args constructor
    public ExerciseDetailsDao(Long id, String name, String primaryMuscle, String secondaryMuscle) {
        this.id = id;
        this.name = name;
        this.primaryMuscle = primaryMuscle;
        this.secondaryMuscle = secondaryMuscle;
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

    public String getPrimaryMuscle() {
        return primaryMuscle;
    }

    public void setPrimaryMuscle(String primaryMuscle) {
        this.primaryMuscle = primaryMuscle;
    }

    public String getSecondaryMuscle() {
        return secondaryMuscle;
    }

    public void setSecondaryMuscle(String secondaryMuscle) {
        this.secondaryMuscle = secondaryMuscle;
    }

    // Builder pattern for compatibility with existing code
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String primaryMuscle;
        private String secondaryMuscle;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder primaryMuscle(String primaryMuscle) {
            this.primaryMuscle = primaryMuscle;
            return this;
        }

        public Builder secondaryMuscle(String secondaryMuscle) {
            this.secondaryMuscle = secondaryMuscle;
            return this;
        }

        public ExerciseDetailsDao build() {
            return new ExerciseDetailsDao(id, name, primaryMuscle, secondaryMuscle);
        }
    }
}
