package com.med.supermedstartup.appointments.application.internal.queryservices;

import com.med.supermedstartup.appointments.domain.model.aggregates.Appointment;
import com.med.supermedstartup.appointments.domain.model.queries.GetAllAppointmentsByDoctorIdQuery;
import com.med.supermedstartup.appointments.domain.model.queries.GetAllAppointmentsByPatientIdQuery;
import com.med.supermedstartup.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.med.supermedstartup.appointments.domain.model.queries.GetAppointmentByIdQuery;
import com.med.supermedstartup.appointments.domain.services.AppointmentQueryService;
import com.med.supermedstartup.appointments.infrastructure.persistance.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService{
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByPatientIdQuery query){
        return appointmentRepository.findAllByPatientId(query.patientId());
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByDoctorIdQuery query){
        return appointmentRepository.findAllByDoctorId(query.doctorId());
    }


    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query){
        return appointmentRepository.findAll();
    }
}
