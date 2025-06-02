package com.med.supermedstartup.profiles.application.internal.commandservices;

import com.med.supermedstartup.profiles.domain.model.aggregates.Patient;
import com.med.supermedstartup.profiles.domain.model.commands.CreatePatientCommand;
import com.med.supermedstartup.profiles.domain.services.PatientCommandService;
import com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository patientRepository;

    public PatientCommandServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> handle(CreatePatientCommand command) {
        var patient = new Patient(command);
        patientRepository.save(patient);
        return Optional.of(patient);
    }
}
