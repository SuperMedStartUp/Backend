package com.med.supermedstartup.profiles.domain.services;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.queries.GetAllPatientsQuery;
import com.med.supermedstartup.profiles.domain.model.queries.GetPatientByIdQuery;
import com.med.supermedstartup.profiles.domain.model.queries.GetPatientByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface PatientQueryService {
    Optional<Patient> handle(GetPatientByIdQuery query);
    Optional<Patient> handle(GetPatientByUserIdQuery query);
    List<Patient> handle(GetAllPatientsQuery query);
}
