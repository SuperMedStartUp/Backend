package com.med.supermedstartup.medicalservice.domain.model.commands;

public record CreateResultCommand(Long doctorId, Long patientId, String typeOfExam, String resultDateTime, boolean result) {
}
