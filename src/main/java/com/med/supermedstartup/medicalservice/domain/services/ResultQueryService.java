package com.med.supermedstartup.medicalservice.domain.services;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Result;

import java.util.List;

public interface ResultQueryService {
    List<Result> getByDoctorId(Long doctorId);
    List<Result> getByPatientId(Long patientId);
}