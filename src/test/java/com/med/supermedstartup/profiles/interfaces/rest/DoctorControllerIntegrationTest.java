package com.med.supermedstartup.profiles.interfaces.rest;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.queries.GetDoctorByIdQuery;
import com.med.supermedstartup.profiles.domain.services.DoctorCommandService;
import com.med.supermedstartup.profiles.domain.services.DoctorQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class DoctorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorQueryService doctorQueryService;

    @MockBean
    private DoctorCommandService doctorCommandService;

    @Test
    void buscarDoctorPorId_mockeado() throws Exception {
        Long id = 1L;
        Doctor mockDoctor = new Doctor("1", "John", "Doe", "john.doe@example.com", "123456789", "ABC123", null);

        when(doctorQueryService.handle(argThat((GetDoctorByIdQuery q) -> q.id().equals(id))))
                .thenReturn(Optional.of(mockDoctor));

        mockMvc.perform(get("/api/v1/doctors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phone").value("123456789"))
                .andExpect(jsonPath("$.licenseNumber").value("ABC123"));

        verify(doctorQueryService).handle(argThat((GetDoctorByIdQuery q) -> q.id().equals(id)));
    }
}

