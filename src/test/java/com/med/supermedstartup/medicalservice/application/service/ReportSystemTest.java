package com.med.supermedstartup.medicalservice.application.service;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Result;
import com.med.supermedstartup.medicalservice.domain.model.commands.CreateResultCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportSystemTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void crearResultCommand_retornaExitosamente() {

        String baseUrl = "http://localhost:" + port + "/api/v1/results";
        CreateResultCommand command = new CreateResultCommand(10L, 2L, "Radiografía", "2023-10-01", true);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJSb2xhbmRvIiwiaWF0IjoxNzQ4OTIyNzk2LCJleHAiOjE3NDk1Mjc1OTZ9.pfSRclBNl7vyI0-fuz2vKr0qQoU39gl5nHCa4kVTb53vrM6Dmcm88Jo_8ZesYNWi");
        HttpEntity<CreateResultCommand> request = new HttpEntity<>(command, headers);

        ResponseEntity<Result> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, Result.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isResult()).isTrue();
    }

    @TestConfiguration
    static class TestRestTemplateConfig {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
