package com.deg.reference.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello-api3/v1")
@Log4j2
@AllArgsConstructor
public class HelloAPI3 {

    @GetMapping(value = "/live")
    public ResponseEntity<String> live() {
        log.info("API3 is up...");
        return new ResponseEntity<>("AP3 is up!", HttpStatus.OK);
    }
}
