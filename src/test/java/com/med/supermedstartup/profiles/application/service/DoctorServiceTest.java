package com.med.supermedstartup.profiles.application.service;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.queries.GetDoctorByIdQuery;
import com.med.supermedstartup.profiles.domain.services.DoctorQueryService;
import com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorQueryService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDoctorById() {
        Long doctorId = 1L;
        Doctor mockDoctor = new Doctor("1", "John", "Doe", "john.doe@example.com", "123456789", "ABC123", "Cardiology");
        GetDoctorByIdQuery query = new GetDoctorByIdQuery(doctorId);

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(mockDoctor));
        when(doctorService.handle(query)).thenReturn(Optional.of(mockDoctor));

        Optional<Doctor> result = doctorService.handle(query);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
        verify(doctorService, times(1)).handle(query);
    }
}