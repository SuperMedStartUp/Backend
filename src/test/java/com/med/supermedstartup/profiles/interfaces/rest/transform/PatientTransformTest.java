package com.med.supermedstartup.profiles.interfaces.rest.transform;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.valueobjects.StreetAddress;
import com.med.supermedstartup.profiles.interfaces.rest.resources.PatientResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTransformTest {

    @Test
    void testTransformToResourceFromEntity() {

        StreetAddress adress = new StreetAddress("Villagran", "12", "Lima", "1234", "Peru");
        Patient patient = new Patient("1", "John", "Doe", "john.doe@example.com", "123456789", adress );

        PatientResource resource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient);

        assertEquals("John Doe", resource.fullName());
        assertEquals("john.doe@example.com", resource.email());
        assertEquals("123456789", resource.phone());
        assertEquals("Villagran 12, Lima, 1234, Peru", resource.streetAddress());
    }
}