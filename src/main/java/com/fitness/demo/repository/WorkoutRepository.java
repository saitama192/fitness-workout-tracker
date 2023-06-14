package com.fitness.demo.repository;

import com.fitness.demo.model.WorkoutDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutDao, Long> {
}
