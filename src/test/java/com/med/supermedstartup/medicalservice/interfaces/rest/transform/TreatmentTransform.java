package com.med.supermedstartup.medicalservice.interfaces.rest.transform;

import com.med.supermedstartup.medicalservice.domain.model.commands.CreateTreatmentCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreatmentTransform {

    @Test
    void toCommandFromResource_Test() {
        String treatmentName = "Terapia de recuperación";
        String description = "Sesión de terapia para la recuperación de lesiones";
        Long patientId = 12L;
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";

        var resource = new CreateTreatmentCommand(treatmentName, description, startDate, endDate, patientId
        );

        var command = new CreateTreatmentCommand(
                resource.treatmentName(),
                resource.description(),
                resource.startDate(),
                resource.endDate(),
                resource.patientId()
        );

        assertEquals(treatmentName, command.treatmentName());
        assertEquals(description, command.description());
        assertEquals(patientId, command.patientId());
        assertEquals(startDate, command.startDate());
        assertEquals(endDate, command.endDate());
    }
}

