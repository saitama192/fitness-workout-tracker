package com.fitness.demo.service;

import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.dto.ExerciseDto;
import com.fitness.demo.exception.WorkoutDoesNotExistsException;
import com.fitness.demo.model.ExerciseDao;
import com.fitness.demo.model.ExerciseDetailsDao;
import com.fitness.demo.model.WorkoutDao;
import com.fitness.demo.repository.ExerciseDetailsRepository;
import com.fitness.demo.repository.WorkoutRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FitnessServiceTest {
    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private ExerciseDetailsRepository exerciseDetailsRepository;

    @InjectMocks
    private FitnessService fitnessService;

    @Test
    public void testSaveExerciseDetails_Success() {
        ExerciseDetailsDto exerciseDetailsDto = new ExerciseDetailsDto("Push-ups", "Chest", "Triceps");

        ExerciseDetailsDao savedExerciseDetails = new ExerciseDetailsDao();
        savedExerciseDetails.setId(1L);
        savedExerciseDetails.setName("Push-ups");
        savedExerciseDetails.setPrimaryMuscle("Chest");
        savedExerciseDetails.setSecondaryMuscle("Triceps");

        when(exerciseDetailsRepository.save(any(ExerciseDetailsDao.class))).thenReturn(savedExerciseDetails);

        ExerciseDetailsDao result = fitnessService.saveExerciseDetails(exerciseDetailsDto);

        assertNotNull(result);
        assertEquals(savedExerciseDetails.getId(), result.getId());
        assertEquals(savedExerciseDetails.getName(), result.getName());
        assertEquals(savedExerciseDetails.getPrimaryMuscle(), result.getPrimaryMuscle());
        assertEquals(savedExerciseDetails.getSecondaryMuscle(), result.getSecondaryMuscle());

        verify(exerciseDetailsRepository, times(1)).save(any(ExerciseDetailsDao.class));
    }

    @Test
    public void testGetExerciseDetails_Success() {
        ExerciseDetailsDao exerciseDetailsDao = new ExerciseDetailsDao();
        exerciseDetailsDao.setId(1L);
        exerciseDetailsDao.setName("Push-ups");
        exerciseDetailsDao.setPrimaryMuscle("Chest");
        exerciseDetailsDao.setSecondaryMuscle("Triceps");

        when(exerciseDetailsRepository.findAll()).thenReturn(Collections.singletonList(exerciseDetailsDao));

        List<ExerciseDetailsDao> result = fitnessService.getExerciseDetails();

        assertNotNull(result);
        assertEquals(1, result.size());
        ExerciseDetailsDao retrievedExerciseDetails = result.get(0);
        assertEquals(exerciseDetailsDao.getId(), retrievedExerciseDetails.getId());
        assertEquals(exerciseDetailsDao.getName(), retrievedExerciseDetails.getName());
        assertEquals(exerciseDetailsDao.getPrimaryMuscle(), retrievedExerciseDetails.getPrimaryMuscle());
        assertEquals(exerciseDetailsDao.getSecondaryMuscle(), retrievedExerciseDetails.getSecondaryMuscle());

        verify(exerciseDetailsRepository, times(1)).findAll();
    }

    @Test
    public void testSaveWorkout_Success() {
        List<ExerciseDto> exerciseDtos = Collections.singletonList(new ExerciseDto("Push-ups", 10, 0, 0));

        List<ExerciseDao> exerciseDaos = Collections.singletonList(
                ExerciseDao.builder()
                        .name("Push-ups")
                        .reps(10)
                        .weightMin(0)
                        .weightMax(0)
                        .build());

        WorkoutDao savedWorkout = new WorkoutDao();
        savedWorkout.setId(1L);
        savedWorkout.setTime(LocalDateTime.now());
        savedWorkout.setExerciseDaos(exerciseDaos);

        when(workoutRepository.save(any(WorkoutDao.class))).thenReturn(savedWorkout);

        WorkoutDao result = fitnessService.saveWorkout(exerciseDtos);

        assertNotNull(result);
        assertEquals(savedWorkout.getId(), result.getId());
        assertEquals(savedWorkout.getTime(), result.getTime());
        assertEquals(savedWorkout.getExerciseDaos(), result.getExerciseDaos());

        verify(workoutRepository, times(1)).save(any(WorkoutDao.class));
    }

    @Test
    public void testDeleteWorkout_Success() {
        Long workoutId = 1L;

        fitnessService.deleteWorkout(workoutId);

        verify(workoutRepository, times(1)).deleteById(workoutId);
    }

    @Test
    public void testFindWorkout_ExistingWorkout_Success() {
        Long workoutId = 1L;

        WorkoutDao workoutDao = new WorkoutDao();
        workoutDao.setId(workoutId);
        workoutDao.setTime(LocalDateTime.now());
        workoutDao.setExerciseDaos(Collections.emptyList());

        when(workoutRepository.findById(workoutId)).thenReturn(Optional.of(workoutDao));

        WorkoutDao result = fitnessService.findWorkout(workoutId);

        assertNotNull(result);
        assertEquals(workoutId, result.getId());
        assertEquals(workoutDao.getTime(), result.getTime());
        assertEquals(workoutDao.getExerciseDaos(), result.getExerciseDaos());

        verify(workoutRepository, times(1)).findById(workoutId);
    }

    @Test
    public void testFindWorkout_NonExistingWorkout_ExceptionThrown() {
        Long workoutId = 1L;

        when(workoutRepository.findById(workoutId)).thenReturn(Optional.empty());

        assertThrows(WorkoutDoesNotExistsException.class, () -> fitnessService.findWorkout(workoutId));

        verify(workoutRepository, times(1)).findById(workoutId);
    }

    @Test
    void updateWorkout() {
        // Create a sample workout
        WorkoutDao existingWorkout = new WorkoutDao();
        existingWorkout.setId(1L);
        existingWorkout.setTime(LocalDateTime.of(2023, 6, 15, 10, 0));
        existingWorkout.setExerciseDaos(Collections.emptyList());

        // Create an updated workout
        WorkoutDao updatedWorkout = new WorkoutDao();
        updatedWorkout.setTime(LocalDateTime.of(2023, 6, 16, 12, 0));
        updatedWorkout.setExerciseDaos(Collections.emptyList());

        // Mock the repository's findById method
        when(workoutRepository.findById(anyLong())).thenReturn(Optional.of(existingWorkout));

        // Mock the repository's save method
        when(workoutRepository.save(existingWorkout)).thenReturn(existingWorkout);

        // Call the updateWorkout method
        WorkoutDao result = fitnessService.updateWorkout(1L, updatedWorkout);

        // Assert that the updated workout has the correct time
        assertEquals(updatedWorkout.getTime(), result.getTime());

        // Assert that the updated workout has the correct exercise list
        assertEquals(updatedWorkout.getExerciseDaos(), result.getExerciseDaos());

    }

    @Test
    public void testGetWorkouts_Success() {
        LocalDateTime date1 = LocalDateTime.of(2023, 6, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 6, 30, 23, 59, 59);

        WorkoutDao workout1 = new WorkoutDao();
        workout1.setId(1L);
        workout1.setTime(LocalDateTime.of(2023, 6, 10, 9, 0, 0));
        WorkoutDao workout2 = new WorkoutDao();
        workout2.setId(2L);
        workout2.setTime(LocalDateTime.of(2023, 6, 15, 16, 30, 0));
        List<WorkoutDao> expectedWorkouts = Arrays.asList(workout1, workout2);

        when(workoutRepository.findByTimeBetween(date1, date2)).thenReturn(expectedWorkouts);

        List<WorkoutDao> result = fitnessService.getWorkouts(date1, date2);

        assertEquals(expectedWorkouts.size(), result.size());
        assertEquals(expectedWorkouts, result);

        verify(workoutRepository, times(1)).findByTimeBetween(date1, date2);
    }
}