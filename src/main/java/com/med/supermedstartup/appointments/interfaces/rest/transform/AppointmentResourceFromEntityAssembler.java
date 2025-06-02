package com.med.supermedstartup.appointments.interfaces.rest.transform;

import com.med.supermedstartup.appointments.domain.model.aggregates.Appointment;
import com.med.supermedstartup.appointments.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment appointment) {
        return new AppointmentResource(
                appointment.getId(),
                appointment.getDoctorId(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getReason());
    }
}
