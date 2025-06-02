package com.med.supermedstartup.medicalservice.domain.services;

import com.med.supermedstartup.medicalservice.domain.model.entities.Report;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetAllReportsQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetReportByIdQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetReportsByPatientIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReportQueryService {
    Optional<Report> handle(GetReportByIdQuery query);
    List<Report> handle(GetReportsByPatientIdQuery query);
    List<Report> handle(GetAllReportsQuery query);
}