package com.med.supermedstartup.medicalservice.application.internal.commandservices;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Treatment;
import com.med.supermedstartup.medicalservice.domain.model.commands.CreateTreatmentCommand;
import com.med.supermedstartup.medicalservice.domain.model.commands.DeleteTreatmentCommand;
import com.med.supermedstartup.medicalservice.domain.services.TreatmentCommandService;
import com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentCommandServiceImpl implements TreatmentCommandService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentCommandServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Optional<Treatment> handle(CreateTreatmentCommand command) {
        if (treatmentRepository.existsByTreatmentNameAndPatientId(command.treatmentName(), command.patientId()))
            throw new IllegalArgumentException("Treatment " + command.treatmentName() + " already exists.");
        var treatment = new Treatment(command);
        treatmentRepository.save(treatment);
        return Optional.of(treatment);
    }

    @Override
    public void handle(DeleteTreatmentCommand command) {
        var treatment=treatmentRepository.findByTreatmentName(command.treatmentId());
        treatment.ifPresent(treatmentRepository::delete);
    }
}
