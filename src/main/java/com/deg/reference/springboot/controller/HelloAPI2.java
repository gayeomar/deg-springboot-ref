package com.deg.reference.springboot.controller;

import com.deg.reference.springboot.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/hello-api2/v1")
@Log4j2
@AllArgsConstructor
public class HelloAPI2 {
    private WebClientService service;


    @GetMapping(value = {"/hello", "hello/{name}"})
    public ResponseEntity<String> hello(@PathVariable(value = "name", required = false) String name) {

        String greeting = "";

        if (name != null && !name.isEmpty()) {
            greeting = "From API2, Hello " + name;
            log.info("*** In API2, we have a guest.");
        } else {
            greeting = "From API2, Hello Stranger";
            log.info("*** In API2, we have a stranger.");
        }

        log.info("API2 checking on API3");
        String api3Return = service.checkLiveFromApi3();
        log.info("API2 checking liveness probe on API3 and received from API3: {}", api3Return);

        return new ResponseEntity<String>(greeting, HttpStatus.OK);
    }

}
