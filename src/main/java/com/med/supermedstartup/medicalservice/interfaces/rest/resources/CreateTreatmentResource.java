package com.med.supermedstartup.medicalservice.interfaces.rest.resources;

public record CreateTreatmentResource(String treatmentName, String description, String startDate, String endDate, Long patientId) {
}
