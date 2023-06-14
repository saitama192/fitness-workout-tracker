package com.fitness.demo.repository;

import com.fitness.demo.model.ExerciseDetailsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseDetailsRepository extends JpaRepository<ExerciseDetailsDao, Long> {
}
