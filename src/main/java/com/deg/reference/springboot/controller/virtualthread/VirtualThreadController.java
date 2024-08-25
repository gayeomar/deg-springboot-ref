package com.deg.reference.springboot.controller.virtualthread;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/virtual-thread/v1")
@Log4j2
public class VirtualThreadController {


    @Value("${spring.application.name}")
    private String applicationName;

    private final RestClient restClient;

    public VirtualThreadController(RestClient.Builder restClientBuilder) {
        restClient = restClientBuilder.baseUrl("https://httpbin.org/").build();
    }

    @GetMapping
    public String getThreadInfo() {
        log.info("Thread Info: {}", Thread.currentThread());
        String returnVal = "Hello from Virtual thread demo : " + applicationName;
        log.info("Response: {}", returnVal);
        return returnVal;
    }

    @GetMapping("/httpbin/block/{seconds}")
    public String delay(@PathVariable int seconds) {
        ResponseEntity<Void> result = restClient.get()
                .uri("/delay/" + seconds)
                .retrieve()
                .toBodilessEntity();

        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());

        return Thread.currentThread().toString();
    }
}

