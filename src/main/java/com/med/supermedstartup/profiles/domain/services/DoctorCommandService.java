package com.med.supermedstartup.profiles.domain.services;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.commands.CreateDoctorCommand;

import java.util.Optional;

public interface DoctorCommandService {
    Optional<Doctor> handle(CreateDoctorCommand command);
}
