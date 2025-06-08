package com.med.supermedstartup.profiles.domain.model;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.valueobjects.StreetAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {
    @Test
    void testPatientCreation() {
        StreetAddress address = new StreetAddress("Avenida Benavides", "23", "Lima", "15002", "Peru");

        Patient patient = new Patient("1", "Jane", "Doe", "jane.doe@example.com", "923456789", address);

        assertEquals("Jane Doe", patient.getFullName());
        assertEquals("jane.doe@example.com", patient.getEmailAddress());
        assertEquals("923456789", patient.getPhone());
        assertEquals(address, patient.getAddress());
    }
}
