package com.med.supermedstartup.profiles.domain.model;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.valueobjects.StreetAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {
    @Test
    void testPatientCreation() {
        StreetAddress address = new StreetAddress("123 Main St", "23", "Lima", "12345", "Peru");

        Patient patient = new Patient("1", "John", "Doe", "john.doe@example.com", "123456789", address);

        assertEquals("John Doe", patient.getFullName());
        assertEquals("john.doe@example.com", patient.getEmailAddress());
        assertEquals("123456789", patient.getPhone());
        assertEquals(address, patient.getAddress());
    }
}
