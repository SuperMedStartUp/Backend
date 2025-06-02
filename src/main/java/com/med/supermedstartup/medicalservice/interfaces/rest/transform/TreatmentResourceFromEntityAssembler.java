package com.med.supermedstartup.medicalservice.interfaces.rest.transform;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Treatment;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.TreatmentResource;

public class TreatmentResourceFromEntityAssembler {
    public static TreatmentResource toResourceFromEntity(Treatment entity){
        return new TreatmentResource(entity.getId(), entity.getTreatmentName(),entity.getDescription(),
                entity.getPeriod(), entity.getPatientId());
    }
}
