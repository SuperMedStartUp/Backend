package com.med.supermedstartup.medicalservice.application.internal.queryservices;

import com.med.supermedstartup.medicalservice.domain.model.entities.Report;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetAllReportsQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetReportByIdQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetReportsByPatientIdQuery;
import com.med.supermedstartup.medicalservice.domain.services.ReportQueryService;
import com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {
    private final ReportRepository reportRepository;

    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    @Override
    public Optional<Report> handle(GetReportByIdQuery query) {
        return reportRepository.findById(query.id());
    }

    @Override
    public List<Report> handle(GetReportsByPatientIdQuery query) {
        return reportRepository.findAllReportsByPatientId(query.patientId());
    }

    @Override
    public List<Report> handle(GetAllReportsQuery query) {
        return reportRepository.findAll();
    }
}
