package com.deg.reference.springboot.controller.virtualthread;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/virtual-thread/v1")
@Log4j2
public class VirtualThreadDemo {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping
    public String getThreadInfo() {
        log.info("Thread Info: {}", Thread.currentThread());
        String returnVal = "Hello from Virtual thread demo : " + applicationName;
        log.info("Response: {}", returnVal);
        return returnVal;
    }
}
