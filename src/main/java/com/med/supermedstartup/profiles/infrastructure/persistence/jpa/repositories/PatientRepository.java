package com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(EmailAddress emailAddress);
    Optional<Patient> findByUserId(Long userId);
}
