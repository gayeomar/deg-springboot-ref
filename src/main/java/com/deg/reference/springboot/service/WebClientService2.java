package com.deg.reference.springboot.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;


@Service
@Log4j2
//@RequiredArgsConstructor
public class WebClientService2 {

    private final WebClient webClient;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.api2.path.hello}")
    private String api2PathHello;

    @Value("${api.api3.path.live}")
    private String api3PathLive;

    public WebClientService2(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiBaseUrl).build();
    }

    public String getHelloFromApi2(String name) {
        String path = (name == null || name.isEmpty()) ? api2PathHello : api2PathHello + "/{name}";

        String api2Return = null;

/*        api2Return = webClientBuilder
                .baseUrl(apiBaseUrl)
                .build()
                .get()
                .uri(path, name)
                .retrieve()
                .bodyToMono(String.class)
                .block();*/
        api2Return = webClient
                .get()
                .uri(apiBaseUrl + path, name)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return api2Return;
    }

    public String checkLiveFromApi3() {
        String api3Return = null;

/*        api3Return = webClientBuilder
                .baseUrl(apiBaseUrl + api3PathLive)
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();*/
        api3Return = webClient
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return api3Return;
    }
}
