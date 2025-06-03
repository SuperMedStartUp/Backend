package com.med.supermedstartup.profiles.interfaces.rest;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.queries.GetPatientByIdQuery;
import com.med.supermedstartup.profiles.domain.model.valueobjects.StreetAddress;
import com.med.supermedstartup.profiles.domain.services.PatientCommandService;
import com.med.supermedstartup.profiles.domain.services.PatientQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private PatientQueryService patientQueryService;

    @Mock
    private PatientCommandService patientCommandService;


    @InjectMocks
    private PatientsController patientsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientsController).build();
    }

    @Test
    void testGetPatientById() throws Exception {
        Long patientId = 1L;
        StreetAddress adress = new StreetAddress("Villagran", "12", "Lima", "1234", "Peru");

        Patient mockPatient = new Patient("1", "John", "Doe", "john.doe@example.com", "123456789", adress);

        ArgumentMatcher<GetPatientByIdQuery> matcher = query -> query.id().equals(patientId);

        when(patientQueryService.handle(argThat(matcher)))
                .thenReturn(Optional.of(mockPatient));

        mockMvc.perform(get("/api/v1/patients/{id}", patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phone").value("123456789"));

        verify(patientQueryService).handle(argThat((GetPatientByIdQuery q) -> q.id().equals(patientId)));

    }

}
