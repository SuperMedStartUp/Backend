package com.med.supermedstartup.medicalservice.interfaces.rest.resources;


public record TreatmentResource(Long id, String treatmentName, String description, String period, Long patientId) {
}
