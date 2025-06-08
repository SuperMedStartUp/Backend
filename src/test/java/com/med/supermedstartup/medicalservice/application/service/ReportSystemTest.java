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
        CreateResultCommand command = new CreateResultCommand(1L, 2L, "Resonancia", "2023-10-02", true);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJDYXJvbCIsImlhdCI6MTc0OTM0MzczMywiZXhwIjoxNzQ5OTQ4NTMzfQ.RWbXrc0eVmCgPanPAVoDos2SPaWxvWzs47p9levXBjh9kM1t_yAVMC2cm-L0HV5Q");
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
