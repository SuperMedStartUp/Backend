package com.med.supermedstartup.medicalservice.application.internal.queryservices;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Treatment;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetAllTreatmentsQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetTreatmentByPatientIdQuery;
import com.med.supermedstartup.medicalservice.domain.services.TreatmentQueryService;
import com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentQueryServiceImpl implements TreatmentQueryService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentQueryServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        return treatmentRepository.findAll();
    }

    @Override
    public List<Treatment> handle(GetTreatmentByPatientIdQuery query) {
        return treatmentRepository.findByPatientId(query.patientId());
    }
}
