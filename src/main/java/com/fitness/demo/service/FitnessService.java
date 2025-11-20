package com.fitness.demo.service;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.dto.ExerciseDto;
import com.fitness.demo.exception.WorkoutDoesNotExistsException;
import com.fitness.demo.model.ExerciseDao;
import com.fitness.demo.model.ExerciseDetailsDao;
import com.fitness.demo.model.WorkoutDao;
import com.fitness.demo.exception.ExerciseDetailsAlreadyExistException;
import com.fitness.demo.repository.ExerciseDetailsRepository;
import com.fitness.demo.repository.ExerciseRepository;
import com.fitness.demo.repository.UserRepository;
import com.fitness.demo.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FitnessService {
    @Autowired
    private ExerciseDetailsRepository exerciseDetailsRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    public ExerciseDetailsDao saveExerciseDetails(ExerciseDetailsDto exerciseDetailsDto) {
        ExerciseDetailsDao newExerciseDetailsDao = ExerciseDetailsDao.builder().name(exerciseDetailsDto.name())
                .primaryMuscle(exerciseDetailsDto.primaryMuscle())
                .secondaryMuscle(exerciseDetailsDto.secondaryMuscle())
                .build();
        try {
            return exerciseDetailsRepository.save(newExerciseDetailsDao);
        } catch (Exception e) {
            throw new ExerciseDetailsAlreadyExistException("The exercise already exists");
        }
    }

    public List<ExerciseDetailsDao> getExerciseDetails() {
        return exerciseDetailsRepository.findAll();
    }

    // in Progress
    public WorkoutDao saveWorkout(List<ExerciseDto> exerciseDtos) {
        // convert exercisedtos to dao and then pass for saving
        List<ExerciseDao> exerciseDaos = exerciseDaoBuilder(exerciseDtos);
        WorkoutDao workoutDao = WorkoutDao.builder().time(LocalDateTime.now()).exerciseDaos(exerciseDaos).build();
        return workoutRepository.save(workoutDao);
    }

    private List<ExerciseDao> exerciseDaoBuilder(List<ExerciseDto> exerciseDtos) {
        return exerciseDtos.stream()
                .map(exerciseDto -> ExerciseDao.builder()
                        .name(exerciseDto.name())
                        .reps(exerciseDto.reps())
                        .weightMax(exerciseDto.weightMax())
                        .weightMin(exerciseDto.weightMin())
                        .build())
                .collect(Collectors.toList());
    }

    public String deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
        return ("Workout" + id + " Record Deleted");
    }

    public WorkoutDao findWorkout(Long id) {
        Optional<WorkoutDao> workoutDaoOptional = workoutRepository.findById(id);
        if (workoutDaoOptional.isPresent()) {
            return workoutDaoOptional.get();
        } else {
            throw new WorkoutDoesNotExistsException("Workout does not exist");
        }
    }

    public WorkoutDao updateWorkout(Long workoutId, WorkoutDao updatedWorkout) {
        WorkoutDao existingWorkout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutDoesNotExistsException("Workout not found"));

        existingWorkout.setTime(updatedWorkout.getTime());
        existingWorkout.setExerciseDaos(updatedWorkout.getExerciseDaos());

        return workoutRepository.save(existingWorkout);
    }

    public List<WorkoutDao> getWorkouts(LocalDateTime date1, LocalDateTime date2) {
        return workoutRepository.findByTimeBetween(date1, date2);
    }
}
