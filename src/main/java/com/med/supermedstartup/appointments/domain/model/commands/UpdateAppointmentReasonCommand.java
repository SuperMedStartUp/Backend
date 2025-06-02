package com.med.supermedstartup.appointments.domain.model.commands;

public record UpdateAppointmentReasonCommand(Long appointmentId, String reason) {
}
