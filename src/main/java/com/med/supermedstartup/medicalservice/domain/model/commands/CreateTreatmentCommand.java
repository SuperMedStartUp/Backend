package com.med.supermedstartup.medicalservice.domain.model.commands;

public record CreateTreatmentCommand(String treatmentName, String description, String startDate, String endDate, Long patientId) {
}
