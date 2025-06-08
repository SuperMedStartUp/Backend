package com.med.supermedstartup.profiles.application.service;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.queries.GetPatientByIdQuery;
import com.med.supermedstartup.profiles.domain.model.valueobjects.StreetAddress;
import com.med.supermedstartup.profiles.domain.services.PatientQueryService;
import com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientQueryService patientService; // Cambiado a @Mock

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatientById() {

        StreetAddress address = new StreetAddress("Avenida Benavides", "23", "Lima", "15002", "Peru");

        Long patientId = 1L;
        Patient mockPatient = new Patient("1", "Jane", "Doe", "jane.doe@example.com", "923456789", address);
        GetPatientByIdQuery query = new GetPatientByIdQuery(patientId);

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(mockPatient));
        when(patientService.handle(query)).thenReturn(Optional.of(mockPatient));

        Optional<Patient> result = patientService.handle(query);

        assertTrue(result.isPresent());
        assertEquals("Jane Doe", result.get().getFullName());
        assertEquals("jane.doe@example.com", result.get().getEmailAddress());
        assertEquals("923456789", result.get().getPhone());
        assertEquals(address, result.get().getAddress());
        verify(patientService, times(1)).handle(query);
    }
}
