package com.med.supermedstartup.iam.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.med.supermedstartup.iam.interfaces.rest.resources.SignUpResource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearDoctorCorrectamenteAtravésDelEndPoint() throws Exception {
        SignUpResource resource = new SignUpResource("doctor1", "password123", "DOCTOR");

        mockMvc.perform(post("/api/v1/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("doctor1"))
                .andExpect(jsonPath("$.role").value("DOCTOR"));
    }


    @Test
    void obtenerTodosLosUsuarios() throws Exception {
        SignUpResource doctorResource = new SignUpResource("doctor1", "password123", "DOCTOR");
        SignUpResource patientResource = new SignUpResource("patient1", "password456", "PATIENT");

        mockMvc.perform(post("/api/v1/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorResource)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientResource)))
                .andExpect(status().isCreated());

        // Luego obtenemos todos los usuarios
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").exists())
                .andExpect(jsonPath("$[1].username").exists());
    }

}