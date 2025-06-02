package com.med.supermedstartup.profiles.application.internal.commandservices;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.commands.CreateDoctorCommand;
import com.med.supermedstartup.profiles.domain.services.DoctorCommandService;
import com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorCommandServiceImpl implements DoctorCommandService {
    private final DoctorRepository doctorRepository;

    public DoctorCommandServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> handle(CreateDoctorCommand command) {
        var doctor = new Doctor(command);
        doctorRepository.save(doctor);
        return Optional.of(doctor);
    }
}
