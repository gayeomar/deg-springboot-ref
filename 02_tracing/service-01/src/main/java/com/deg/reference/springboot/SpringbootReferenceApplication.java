package com.deg.reference.springboot;

import com.deg.reference.springboot.config.HelloProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@SpringBootApplication
@EnableConfigurationProperties(HelloProperties.class)
public class SpringbootReferenceApplication {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringbootReferenceApplication.class, args);
        // Arrays.asList(ctx.getBeanDefinitionNames()).stream().sorted().forEach(System.out::println);
    }
}
