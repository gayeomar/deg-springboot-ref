package com.deg.reference.springboot;

import com.deg.reference.springboot.config.HelloProperties;
import com.deg.reference.springboot.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@SpringBootApplication
@EnableConfigurationProperties(HelloProperties.class)
public class SpringbootReferenceApplication implements CommandLineRunner{

    @Autowired
    HelloProperties helloProperties;

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringbootReferenceApplication.class, args);
       // Arrays.asList(ctx.getBeanDefinitionNames()).stream().sorted().forEach(System.out::println);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("********: " + helloProperties.getApi2PathHello());
    }
    @Bean
    //@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    //@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS) //_SESSION _APPLICATION
    //@RequestScope
    public Person personSingleton(){
        return new Person();
    }

/*    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }*/

}
