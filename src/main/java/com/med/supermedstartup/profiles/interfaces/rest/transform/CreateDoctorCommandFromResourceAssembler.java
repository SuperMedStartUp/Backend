package com.med.supermedstartup.profiles.interfaces.rest.transform;

import com.med.supermedstartup.profiles.domain.model.commands.CreateDoctorCommand;
import com.med.supermedstartup.profiles.interfaces.rest.resources.CreateDoctorResource;

public class CreateDoctorCommandFromResourceAssembler {
    public static CreateDoctorCommand toCommandFromResource(CreateDoctorResource resource) {
        return new CreateDoctorCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phone(),
                resource.specialty(),
                resource.licenceNumber(),
                resource.userId()
        );
    }
}
