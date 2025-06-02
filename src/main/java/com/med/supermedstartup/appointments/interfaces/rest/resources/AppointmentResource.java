package com.med.supermedstartup.appointments.interfaces.rest.resources;

import java.util.Date;

public record AppointmentResource(
        Long id,
        Long doctorId,
        Long patientId,
        String date,
        String reason
) { }
