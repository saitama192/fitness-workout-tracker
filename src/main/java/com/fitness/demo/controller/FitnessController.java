package com.fitness.demo.controller;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.entity.Exercise;
import com.fitness.demo.entity.ExerciseDetails;
import com.fitness.demo.service.FitnessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class FitnessController {
    @Autowired
    private FitnessService fitnessService;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    @PostMapping("workout")
    public ResponseEntity addWorkout()
    {
        return ResponseEntity.ok().body("Hi user");
    }

//    @PostMapping("exercise/add")
//    public ResponseEntity addExercise(Exercise exercise)
//    {
//        return ResponseEntity.ok().body("Hi user");
//    }

    @PostMapping("exercisedetails/add")
    public ResponseEntity<ExerciseDetails> addExerciseDetails(@RequestBody ExerciseDetailsDto exerciseDetailsDto)
    {
        log.info("About to add Exercise Details");
        ExerciseDetails newexerciseDetails = fitnessService.saveExerciseDetails(exerciseDetailsDto);
        log.info("Adding Exercise Details");
        return ResponseEntity.status(201).header(CONTENT_TYPE, APPLICATION_JSON).body(newexerciseDetails);
        //Status code 201 signifies that request has succeeded and has led to the creation of a resource
    }
    @GetMapping("exercisedetails/alldetails")
    public ResponseEntity<List<ExerciseDetails>> getAllExerciseDetails(){
        log.info("About to get Exercise Details");
        List<ExerciseDetails> exerciseDetails = fitnessService.getExerciseDetails();
        return ResponseEntity.status(200).header(CONTENT_TYPE, APPLICATION_JSON).body(exerciseDetails);
    }
}
