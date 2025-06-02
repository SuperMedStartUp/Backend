package com.med.supermedstartup.profiles.domain.services;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.queries.GetAllDoctorsQuery;
import com.med.supermedstartup.profiles.domain.model.queries.GetDoctorByIdQuery;
import com.med.supermedstartup.profiles.domain.model.queries.GetDoctorByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface DoctorQueryService {
    Optional<Doctor> handle(GetDoctorByIdQuery query);
    Optional<Doctor> handle(GetDoctorByUserIdQuery query);
    List<Doctor> handle(GetAllDoctorsQuery query);
}
