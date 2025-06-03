package com.med.supermedstartup.steps;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class AppTestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}