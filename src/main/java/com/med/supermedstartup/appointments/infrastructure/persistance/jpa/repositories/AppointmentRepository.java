package com.med.supermedstartup.appointments.infrastructure.persistance.jpa.repositories;

import com.med.supermedstartup.appointments.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByDoctorId(Long id);
    List<Appointment> findAllByPatientId(Long id);
    boolean existsByDate(String date);
}

