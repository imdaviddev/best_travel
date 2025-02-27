package com.practicando.best_travel.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alive")
public class CheckAlive {

    @GetMapping
    public ResponseEntity<?> isAlive(){
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
