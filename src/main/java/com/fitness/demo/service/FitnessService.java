package com.fitness.demo.service;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.dto.ExerciseDto;
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
        ExerciseDetailsDao newExerciseDetailsDao = ExerciseDetailsDao.builder().name(exerciseDetailsDto.getName())
                .primaryMuscle(exerciseDetailsDto.getPrimaryMuscle())
                .secondaryMuscle(exerciseDetailsDto.getSecondaryMuscle())
                .build();
        try{
        return exerciseDetailsRepository.save(newExerciseDetailsDao);
        }
        catch (Exception e){
            throw new ExerciseDetailsAlreadyExistException("The exercise already exists");
        }
    }


    public List<ExerciseDetailsDao> getExerciseDetails() {
        return exerciseDetailsRepository.findAll();
    }

    //in Progress
    public WorkoutDao saveWorkout(List<ExerciseDto> exerciseDtos) {
        //convert exercisedtos to dao and then pass for saving
        List<ExerciseDao> exerciseDaos = exerciseDaoBuilder(exerciseDtos);
        WorkoutDao workoutDao = WorkoutDao.builder().time(LocalDateTime.now()).exerciseDaos(exerciseDaos).build();
        return workoutRepository.save(workoutDao);
    }
    private List<ExerciseDao> exerciseDaoBuilder(List<ExerciseDto> exerciseDtos){
        return exerciseDtos.stream()
                .map(exerciseDto -> ExerciseDao.builder()
                        .name(exerciseDto.getName())
                        .reps(exerciseDto.getReps())
                        .weightMax(exerciseDto.getWeightMax())
                        .weightMin(exerciseDto.getWeightMin())
                        .build())
                .collect(Collectors.toList());
    }

}
