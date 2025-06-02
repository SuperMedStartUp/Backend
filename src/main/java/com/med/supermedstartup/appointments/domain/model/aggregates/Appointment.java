package com.med.supermedstartup.appointments.domain.model.aggregates;

import com.med.supermedstartup.appointments.domain.model.commands.CreateAppointmentCommand;
import com.med.supermedstartup.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    private Long doctorId;

    private Long patientId;

    private String date;

    private String reason;


    public Appointment() {}

    public Appointment(CreateAppointmentCommand command){
        this.doctorId = command.doctorId();
        this.patientId = command.patientId();
        this.date = command.date();
        this.reason = command.reason();
    }

    public Appointment updateReason(String reason) {
        this.reason = reason;
        return this;
    }
}
