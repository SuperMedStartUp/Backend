package com.med.supermedstartup.medicalservice.application.internal.commandservices;

import com.med.supermedstartup.medicalservice.domain.model.commands.CreateReportCommand;
import com.med.supermedstartup.medicalservice.domain.model.entities.Report;
import com.med.supermedstartup.medicalservice.domain.services.ReportCommandService;
import com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        if (reportRepository.existsByDateAndReasonAndPatientId(command.date(), command.reason(), command.patientId())){
            throw new IllegalArgumentException("Report already exists");
        }
        var report = new Report(command);
        var createdReport = reportRepository.save(report);
        return Optional.of(createdReport);
    }
}
