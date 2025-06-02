package com.med.supermedstartup.medicalservice.domain.model.aggregates;


import com.med.supermedstartup.medicalservice.domain.model.commands.CreateResultCommand;
import com.med.supermedstartup.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Result extends AuditableAbstractAggregateRoot<Result> {
    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    @NotNull
    private String typeOfExam;

    @NotNull
    private String resultDateTime;

    @NotNull
    private boolean result;

    public Result(CreateResultCommand command) {
        this.doctorId = command.doctorId();
        this.patientId = command.patientId();
        this.typeOfExam = command.typeOfExam();
        this.resultDateTime = command.resultDateTime();
        this.result = command.result();
    }

    public Result() {
    }

}