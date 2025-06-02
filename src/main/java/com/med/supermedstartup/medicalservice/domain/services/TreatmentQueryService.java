package com.med.supermedstartup.medicalservice.domain.services;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Treatment;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetAllTreatmentsQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetTreatmentByPatientIdQuery;

import java.util.List;

public interface TreatmentQueryService {
    List<Treatment> handle(GetAllTreatmentsQuery query);
    List<Treatment> handle(GetTreatmentByPatientIdQuery query);
}
