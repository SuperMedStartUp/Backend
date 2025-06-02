package com.med.supermedstartup.medicalservice.interfaces.rest.transform;

import com.med.supermedstartup.medicalservice.domain.model.commands.CreateResultCommand;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.CreateResultResource;

public class CreateResultCommandFromResourceAssembler {
    public static CreateResultCommand toCommandFromResource(CreateResultResource resource) {
        return new CreateResultCommand(resource.doctorId(), resource.patientId(), resource.typeOfExam(),
                resource.resultDateTime(), resource.result());
    }
}
