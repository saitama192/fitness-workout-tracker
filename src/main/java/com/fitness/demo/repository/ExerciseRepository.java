package com.fitness.demo.repository;

import com.fitness.demo.model.ExerciseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseDao, Long> {
}
