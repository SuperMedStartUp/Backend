package com.med.supermedstartup.medicalservice.domain.model;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Treatment;
import com.med.supermedstartup.medicalservice.domain.model.commands.CreateTreatmentCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreatmentTest {

    @Test
    void crearTreatment() {
        Treatment treatment = new Treatment("Realizar una cirugía", "Se realizará una cirugía para reparar la fractura del la nariz", "2025-10-01", "2025-10-15");
        assertEquals("Realizar una cirugía", treatment.getTreatmentName());
        assertEquals("Se realizará una cirugía para reparar la fractura del la nariz", treatment.getDescription());
        assertEquals("From " + "2025-10-01" + " to " + "2025-10-15", treatment.getPeriod());
    }

    @Test
    void crearTreatmentConCommand() {
        CreateTreatmentCommand command = new CreateTreatmentCommand(
                "Realizar una cirugía",
                "Se realizará una cirugía para reparar la fractura del la nariz",
                "2025-10-01",
                "2025-10-15",
                1L
        );

        Treatment treatment = new Treatment(command);

        assertEquals("Realizar una cirugía", treatment.getTreatmentName());
        assertEquals("Se realizará una cirugía para reparar la fractura del la nariz", treatment.getDescription());
        assertEquals("From 2025-10-01 to 2025-10-15", treatment.getPeriod());
        assertEquals(1L, treatment.getPatientId());
    }
}