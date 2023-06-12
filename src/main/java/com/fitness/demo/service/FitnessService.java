package com.fitness.demo.service;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.entity.ExerciseDetails;
import com.fitness.demo.exception.ExerciseDetailsAlreadyExistException;
import com.fitness.demo.repository.ExerciseDetailsRepository;
import com.fitness.demo.repository.ExerciseRepository;
import com.fitness.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessService {
    @Autowired
    private ExerciseDetailsRepository exerciseDetailsRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;

    public ExerciseDetails saveExerciseDetails(ExerciseDetailsDto exerciseDetailsDto) {
        ExerciseDetails newExerciseDetails = ExerciseDetails.builder().name(exerciseDetailsDto.getName())
                .primaryMuscle(exerciseDetailsDto.getPrimaryMuscle())
                .secondaryMuscle(exerciseDetailsDto.getSecondaryMuscle())
                .build();
        try{
        return exerciseDetailsRepository.save(newExerciseDetails);
        }
        catch (Exception e){
            throw new ExerciseDetailsAlreadyExistException("The exercise already exists");
        }
    }



}
