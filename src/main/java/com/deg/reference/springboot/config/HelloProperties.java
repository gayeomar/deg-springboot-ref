package com.deg.reference.springboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="com.deg")
@Getter
@Setter
public class HelloProperties {
    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.api2.path.hello}")
    private String api2PathHello;

    @Value("${api.api3.path.live}")
    private String api3PathLive;
}