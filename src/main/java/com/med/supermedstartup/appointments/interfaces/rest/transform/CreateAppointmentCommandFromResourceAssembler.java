package com.med.supermedstartup.appointments.interfaces.rest.transform;

import com.med.supermedstartup.appointments.domain.model.commands.CreateAppointmentCommand;
import com.med.supermedstartup.appointments.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                resource.doctorId(),
                resource.patientId(),
                resource.date(),
                resource.reason()
                );
    }
}
