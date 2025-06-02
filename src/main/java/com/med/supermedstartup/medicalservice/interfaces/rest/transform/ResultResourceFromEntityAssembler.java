package com.med.supermedstartup.medicalservice.interfaces.rest.transform;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Result;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.ResultResource;

public class ResultResourceFromEntityAssembler {
    public static ResultResource toResourceFromEntity(Result result) {
        return new ResultResource(
                result.getId(),
                result.getDoctorId(),
                result.getPatientId(),
                result.getTypeOfExam(),
                result.getResultDateTime(),
                result.isResult());
    }
}