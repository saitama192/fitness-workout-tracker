package com.fitness.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @GetMapping("hello")
    public ResponseEntity helloUser(){
        return ResponseEntity.ok().body("Hi user");
    }
}
