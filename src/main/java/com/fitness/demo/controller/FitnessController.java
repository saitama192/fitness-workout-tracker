package com.fitness.demo.controller;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.dto.ExerciseDto;
import com.fitness.demo.model.ExerciseDetailsDao;
import com.fitness.demo.model.WorkoutDao;
import com.fitness.demo.service.FitnessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("workout/add")
    public ResponseEntity<WorkoutDao> addWorkout(@RequestBody List<ExerciseDto> exercises){
        log.info("About to add workout");
        WorkoutDao workoutDao = fitnessService.saveWorkout(exercises);
        return ResponseEntity.status(201).header(CONTENT_TYPE, APPLICATION_JSON).body(workoutDao);
    }
    @DeleteMapping("workout/delete/{id}")
    public ResponseEntity deleteWorkout(@PathVariable Long id){
        String message = fitnessService.deleteWorkout(id);
        return ResponseEntity.status(200).header(CONTENT_TYPE, APPLICATION_JSON).body(message);
    }
    @GetMapping("workout/{id}")
    public ResponseEntity getWorkout(@PathVariable Long id){
        WorkoutDao workoutDao = fitnessService.findWorkout(id);
        return ResponseEntity.status(200).header(CONTENT_TYPE, APPLICATION_JSON).body(workoutDao);
    }

    @PutMapping("workout/{workoutId}")
    public ResponseEntity<WorkoutDao> updateWorkout(@PathVariable Long workoutId, @RequestBody WorkoutDao updatedWorkout) {
        WorkoutDao updated = fitnessService.updateWorkout(workoutId, updatedWorkout);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("workout/get/")
    public ResponseEntity<List<WorkoutDao>> getWorkout(@RequestParam("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date1,
                                                       @RequestParam("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date2){
        log.info("Request received for date {} and {}",date1, date2);
        List<WorkoutDao> workoutDaoList = fitnessService.getWorkouts(date1, date2);
        return ResponseEntity.status(200).header(CONTENT_TYPE, APPLICATION_JSON).body(workoutDaoList);
    }

}
