package com.med.supermedstartup.medicalservice.application.internal.queryservices;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Result;
import com.med.supermedstartup.medicalservice.domain.services.ResultQueryService;
import com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultQueryServiceImpl implements ResultQueryService {
    private final ResultRepository resultRepository;

    public ResultQueryServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> getByDoctorId(Long doctorId) {
        return resultRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Result> getByPatientId(Long patientId) {
        return resultRepository.findByPatientId(patientId);
    }
}