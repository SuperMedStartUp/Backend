package com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByLicenseNumber(String licenceNumber);
    boolean existsByEmail(EmailAddress emailAddress);
    Optional<Doctor> findByUserId(Long userId);
}
