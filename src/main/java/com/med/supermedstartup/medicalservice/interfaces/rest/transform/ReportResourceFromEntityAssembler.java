package com.med.supermedstartup.medicalservice.interfaces.rest.transform;

import com.med.supermedstartup.medicalservice.domain.model.entities.Report;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {
    public static ReportResource toResourceFromEntity(Report entity){
        return new ReportResource(entity.getId(), entity.getReason(), entity.getDate(), entity.getPatientId());
    }
}
