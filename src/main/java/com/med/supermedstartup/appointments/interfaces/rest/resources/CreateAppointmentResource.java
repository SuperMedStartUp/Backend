package com.med.supermedstartup.appointments.interfaces.rest.resources;

import java.util.Date;

public record CreateAppointmentResource(
        Long doctorId,
        Long patientId,
        String date,
        String reason
){ }
