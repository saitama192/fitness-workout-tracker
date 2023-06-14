package com.fitness.demo.controller;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.dto.ExerciseDto;
import com.fitness.demo.model.ExerciseDetailsDao;
import com.fitness.demo.model.WorkoutDao;
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
    public ResponseEntity<ExerciseDetailsDao> addExerciseDetails(@RequestBody ExerciseDetailsDto exerciseDetailsDto)
    {
        log.info("About to add Exercise Details");
        ExerciseDetailsDao newexerciseDetailsDao = fitnessService.saveExerciseDetails(exerciseDetailsDto);
        log.info("Adding Exercise Details");
        return ResponseEntity.status(201).header(CONTENT_TYPE, APPLICATION_JSON).body(newexerciseDetailsDao);
        //Status code 201 signifies that request has succeeded and git chas led to the creation of a resource
    }
    @GetMapping("exercisedetails/alldetails")
    public ResponseEntity<List<ExerciseDetailsDao>> getAllExerciseDetails(){
        log.info("About to get Exercise Details");
        List<ExerciseDetailsDao> exerciseDetailDaos = fitnessService.getExerciseDetails();
        return ResponseEntity.status(200).header(CONTENT_TYPE, APPLICATION_JSON).body(exerciseDetailDaos);
    }

    @PostMapping("workout/addworkout")
    public ResponseEntity<WorkoutDao> addWorkout(@RequestBody List<ExerciseDto> exercises){
        log.info("About to add workout");
        WorkoutDao workoutDao = fitnessService.saveWorkout(exercises);
        return ResponseEntity.status(201).header(CONTENT_TYPE, APPLICATION_JSON).body(workoutDao);
    }
}
