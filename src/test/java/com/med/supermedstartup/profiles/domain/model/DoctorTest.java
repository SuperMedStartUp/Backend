package com.med.supermedstartup.profiles.domain.model;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DoctorTest {

    @Test
    void testDoctorCreation() {
        Doctor doctor = new Doctor("1", "John", "Doe", "john.doe@example.com", "123456789", "ABC123", null);

        assertEquals("John Doe", doctor.getFullName());
        assertEquals("john.doe@example.com", doctor.getEmailAddress());
        assertEquals("123456789", doctor.getPhone());
        assertEquals("ABC123", doctor.getLicenseNumber());
        assertNull(doctor.getSpecialty()); // Specialty is null in this test
    }
}