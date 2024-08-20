package com.deg.reference.springboot.domain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;

    @PostConstruct
    public void init() throws Exception{
        System.out.println(
                "Bean Person has been "
                        + "instantiated and I'm "
                        + "the init() method");
    }

    @PreDestroy
    public void destroy() throws Exception{
        System.out.println(
                "Container has been closed "
                        + "and I'm the destroy() method");
    }

}
