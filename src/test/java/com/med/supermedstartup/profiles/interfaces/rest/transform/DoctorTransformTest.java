package com.med.supermedstartup.profiles.interfaces.rest.transform;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.interfaces.rest.resources.DoctorResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorTransformTest {

    @Test
    void testTransformToResourceFromEntity() {
        Doctor doctor = new Doctor("1", "John", "Doe", "john.doe@example.com", "923456789", "ABC123", "Cardiologia");
        DoctorResource resource = DoctorResourceFromEntityAssembler.toResourceFromEntity(doctor);

        assertEquals("John Doe", resource.fullName());
        assertEquals("john.doe@example.com", resource.email());
        assertEquals("923456789", resource.phone());
        assertEquals("ABC123", resource.licenseNumber());
        assertEquals("Cardiologia", resource.specialty());
    }
}
