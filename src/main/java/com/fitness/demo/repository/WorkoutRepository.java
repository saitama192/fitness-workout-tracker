package com.fitness.demo.repository;

import com.fitness.demo.model.WorkoutDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutDao, Long> {
    @Query("SELECT w FROM WorkoutDao w WHERE w.time BETWEEN ?1 AND ?2")
    List<WorkoutDao> findByTimeBetween(LocalDateTime date1, LocalDateTime date2);
}
