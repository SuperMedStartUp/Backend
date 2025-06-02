package com.med.supermedstartup.appointments.domain.services;

import com.med.supermedstartup.appointments.domain.model.aggregates.Appointment;
import com.med.supermedstartup.appointments.domain.model.commands.CreateAppointmentCommand;
import com.med.supermedstartup.appointments.domain.model.commands.DeleteAppointmentCommand;
import com.med.supermedstartup.appointments.domain.model.commands.UpdateAppointmentReasonCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Optional<Appointment> handle(CreateAppointmentCommand command);
    Optional<Appointment> handle(UpdateAppointmentReasonCommand command);
    void handle(DeleteAppointmentCommand command);
}
