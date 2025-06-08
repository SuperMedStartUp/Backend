package com.med.supermedstartup.profiles.interfaces.rest.transform;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.valueobjects.StreetAddress;
import com.med.supermedstartup.profiles.interfaces.rest.resources.PatientResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTransformTest {

    @Test
    void testTransformToResourceFromEntity() {

        StreetAddress address = new StreetAddress("Avenida Benavides", "23", "Lima", "15002", "Peru");

        Patient patient = new Patient("1", "Jane", "Doe", "jane.doe@example.com", "923456789", address);

        PatientResource resource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient);

        assertEquals("Jane Doe", resource.fullName());
        assertEquals("jane.doe@example.com", resource.email());
        assertEquals("923456789", resource.phone());
        assertEquals("Avenida Benavides 23, Lima, 15002, Peru", resource.streetAddress());
    }
}