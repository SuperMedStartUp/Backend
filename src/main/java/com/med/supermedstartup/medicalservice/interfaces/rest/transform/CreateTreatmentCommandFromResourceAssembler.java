package com.med.supermedstartup.medicalservice.interfaces.rest.transform;

import com.med.supermedstartup.medicalservice.domain.model.commands.CreateTreatmentCommand;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.CreateTreatmentResource;

public class CreateTreatmentCommandFromResourceAssembler {
    public static CreateTreatmentCommand toCommandFromResource(CreateTreatmentResource resource){
        return new CreateTreatmentCommand(resource.treatmentName(), resource.description(), resource.startDate(), resource.endDate(), resource.patientId());
    }
}
