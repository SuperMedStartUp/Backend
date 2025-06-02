package com.med.supermedstartup.profiles.application.internal.queryservices;

import com.med.supermedstartup.profiles.domain.model.aggregates.Doctor;
import com.med.supermedstartup.profiles.domain.model.queries.GetAllDoctorsQuery;
import com.med.supermedstartup.profiles.domain.model.queries.GetDoctorByIdQuery;
import com.med.supermedstartup.profiles.domain.model.queries.GetDoctorByUserIdQuery;
import com.med.supermedstartup.profiles.domain.services.DoctorQueryService;
import com.med.supermedstartup.profiles.infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorQueryServiceImpl implements DoctorQueryService {
    private final DoctorRepository doctorRepository;

    public DoctorQueryServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> handle(GetDoctorByIdQuery query) {
        return doctorRepository.findById(query.id());
    }

    @Override
    public Optional<Doctor> handle(GetDoctorByUserIdQuery query) {
        return doctorRepository.findByUserId(query.id());
    }

    @Override
    public List<Doctor> handle(GetAllDoctorsQuery query) {return doctorRepository.findAll();}

}
