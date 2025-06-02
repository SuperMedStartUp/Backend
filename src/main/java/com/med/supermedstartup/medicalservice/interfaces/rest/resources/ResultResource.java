package com.med.supermedstartup.medicalservice.interfaces.rest.resources;

public record ResultResource(
        Long id,
        Long doctorId,
        Long patientId,
        String typeOfExam,
        String ResultDateTime,
        boolean result
) { }