package com.med.supermedstartup.medicalservice.interfaces.rest.resources;

public record CreateResultResource(Long doctorId, Long patientId, String typeOfExam, String resultDateTime, boolean result) {
}
