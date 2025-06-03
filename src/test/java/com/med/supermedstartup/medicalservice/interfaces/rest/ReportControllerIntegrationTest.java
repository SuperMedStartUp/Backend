package com.med.supermedstartup.medicalservice.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.med.supermedstartup.medicalservice.domain.model.commands.CreateReportCommand;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class ReportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearReporteCorrectamenteAtravésDelEndPoint() throws Exception {

        CreateReportCommand command = new CreateReportCommand("Recojo de resultados", "2023-11-11", 3L);


        mockMvc.perform(post("/api/v1/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.reason").value("Recojo de resultados"))
                .andExpect(jsonPath("$.date").value("2023-11-11"))
                .andExpect(jsonPath("$.patientId").value(3L));
    }
}
