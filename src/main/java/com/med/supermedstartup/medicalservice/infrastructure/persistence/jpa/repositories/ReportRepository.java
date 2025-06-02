package com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories;

import com.med.supermedstartup.medicalservice.domain.model.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    boolean existsByDateAndReasonAndPatientId(String date, String reason, Long patientId);
    List<Report> findAllReportsByPatientId(Long patientId);
}
