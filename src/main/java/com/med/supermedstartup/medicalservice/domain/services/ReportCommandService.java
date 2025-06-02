package com.med.supermedstartup.medicalservice.domain.services;

import com.med.supermedstartup.medicalservice.domain.model.commands.CreateReportCommand;
import com.med.supermedstartup.medicalservice.domain.model.entities.Report;

import java.util.Optional;

public interface ReportCommandService {
    Optional<Report> handle(CreateReportCommand command);
}
