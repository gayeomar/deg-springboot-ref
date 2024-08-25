package com.deg.reference.springboot.controller.virtualthread;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/httpbin")
@Log4j2
public class HttpBinController {

    private final RestClient restClient;

    public HttpBinController(RestClient.Builder restClientBuilder) {
        restClient = restClientBuilder.baseUrl("https://httpbin.org/").build();
    }

    @GetMapping("/block/{seconds}")
    public String delay(@PathVariable int seconds) {
        ResponseEntity<Void> result = restClient.get()
                .uri("/delay/" + seconds)
                .retrieve()
                .toBodilessEntity();

        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());

        return Thread.currentThread().toString();
    }
}


