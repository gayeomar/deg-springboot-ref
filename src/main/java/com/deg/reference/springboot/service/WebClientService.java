package com.deg.reference.springboot.service;

import com.deg.reference.springboot.config.HelloProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
@Log4j2
public class WebClientService {
    private WebClient.Builder webClientBuilder;
    private HelloProperties helloProperties;

    public String getHelloFromApi2(String name) {
        String api2PathHello = helloProperties.getApi2PathHello();
        String apiBaseUrl = helloProperties.getApiBaseUrl();
        String path = (name == null || name.isEmpty()) ? api2PathHello : api2PathHello + "/{name}";

        String api2Return = null;

        api2Return = webClientBuilder
                .baseUrl(apiBaseUrl)
                .build()
                .get()
                .uri(path, name)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return api2Return;
    }

    public String checkLiveFromApi3() {
        String api3PathLive = helloProperties.getApi3PathLive();
        String apiBaseUrl = helloProperties.getApiBaseUrl();
        String api3Return = null;

        api3Return = webClientBuilder
                .baseUrl(apiBaseUrl + api3PathLive)
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return api3Return;
    }


}
