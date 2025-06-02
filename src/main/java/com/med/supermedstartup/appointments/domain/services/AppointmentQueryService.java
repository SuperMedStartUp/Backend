package com.med.supermedstartup.appointments.domain.services;


import com.med.supermedstartup.appointments.domain.model.aggregates.Appointment;
import com.med.supermedstartup.appointments.domain.model.queries.GetAllAppointmentsByDoctorIdQuery;
import com.med.supermedstartup.appointments.domain.model.queries.GetAllAppointmentsByPatientIdQuery;
import com.med.supermedstartup.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.med.supermedstartup.appointments.domain.model.queries.GetAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByDoctorIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByPatientIdQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
}
