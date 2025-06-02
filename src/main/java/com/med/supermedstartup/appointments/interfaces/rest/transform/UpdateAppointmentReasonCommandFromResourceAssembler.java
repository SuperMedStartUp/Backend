package com.med.supermedstartup.appointments.interfaces.rest.transform;

import com.med.supermedstartup.appointments.domain.model.commands.UpdateAppointmentReasonCommand;
import com.med.supermedstartup.appointments.interfaces.rest.resources.UpdateAppointmentReasonResource;

public class UpdateAppointmentReasonCommandFromResourceAssembler {
    public static UpdateAppointmentReasonCommand toCommandFromResource(Long appointmentId, UpdateAppointmentReasonResource resource) {
        return new UpdateAppointmentReasonCommand(appointmentId, resource.reason());
    }
}
