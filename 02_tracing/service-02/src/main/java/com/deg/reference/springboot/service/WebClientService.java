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

    public String checkLiveFromApi3() {
        String api3PathLive = helloProperties.getApi3PathLive();
        String api3BaseUrl = helloProperties.getApi3BaseUrl();
        String api3Return = null;

        api3Return = webClientBuilder
                .baseUrl(api3BaseUrl + api3PathLive)
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return api3Return;
    }


}
