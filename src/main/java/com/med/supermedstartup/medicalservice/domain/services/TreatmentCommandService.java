package com.med.supermedstartup.medicalservice.domain.services;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Treatment;
import com.med.supermedstartup.medicalservice.domain.model.commands.CreateTreatmentCommand;
import com.med.supermedstartup.medicalservice.domain.model.commands.DeleteTreatmentCommand;

import java.util.Optional;

public interface TreatmentCommandService {
    Optional<Treatment> handle(CreateTreatmentCommand command);
    void handle(DeleteTreatmentCommand command);
}
