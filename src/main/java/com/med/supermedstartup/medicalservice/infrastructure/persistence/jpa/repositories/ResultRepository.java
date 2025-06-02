package com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByDoctorId(Long doctorId);
    List<Result> findByPatientId(Long patientId);

}