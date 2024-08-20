package com.deg.reference.springboot.controller;

import com.deg.reference.springboot.service.WebClientService;
import com.deg.reference.springboot.service.WebClientService2;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author <a href="mailto:omarg@deguene.com">Omar Gaye</a>
 */
@RestController
@RequestMapping("/api/hello-api1/v1")
@Log4j2
@AllArgsConstructor
public class HelloAPI1 {

    private Tracer tracer;
    private WebClientService service;

    @GetMapping(value = "/simpleHello")
    public String simpleHello() {
        return ("Hello World!");
    }

    @GetMapping(value = {"/hello", "hello/{name}"})
    public ResponseEntity<String> hello(@PathVariable(value = "name", required = false) String name) {
        testTracer();
        String api2Return = service.getHelloFromApi2(name);
        log.info("From API1, received: {}", api2Return);
        return new ResponseEntity<String>(api2Return, HttpStatus.OK);
    }

    public void testTracer() {
        Span currentSpan = tracer.currentSpan();
        String correlationId = currentSpan.context().traceId();
        log.info("Handling request with trace ID: {}", correlationId);
    }

    @GetMapping(value = "/live", produces = "text/plain")
    public String live() {
        return "live";
    }

    @GetMapping(value = "/ready", produces = "text/plain")
    public String ready() {
        return "ready";
    }

}
