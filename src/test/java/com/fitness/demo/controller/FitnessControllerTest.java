package com.fitness.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.demo.dto.ExerciseDetailsDto;
import com.fitness.demo.entity.ExerciseDetails;
import com.fitness.demo.service.FitnessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = FitnessController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class )
class FitnessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FitnessService fitnessService;

    @Autowired
    private ObjectMapper objectMapper;

    private ExerciseDetails exerciseDetails;
    private ExerciseDetailsDto exerciseDetailsDto;

    @BeforeEach
    public void init(){
        exerciseDetailsDto = ExerciseDetailsDto.builder().name("Bench Press").primaryMuscle("Chest").secondaryMuscle("Triceps").build();
        exerciseDetails = ExerciseDetails.builder().name("Bench Press").primaryMuscle("Chest").secondaryMuscle("Triceps").id(2l).build();
    }
    @Disabled
    @Test
    void addWorkout() {
    }

    @Test
    void addExerciseDetailsSuccessTest() throws Exception {
        given(fitnessService.saveExerciseDetails(ArgumentMatchers.any())).willReturn(exerciseDetails);
        ResultActions response = mockMvc.perform(post("/api/v1/exercisedetails/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseDetailsDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bench Press"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.primaryMuscle").value("Chest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.secondaryMuscle").value("Triceps"));

    }
}