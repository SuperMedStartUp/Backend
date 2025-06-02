package com.med.supermedstartup.medicalservice.application.internal.commandservices;

import com.med.supermedstartup.medicalservice.domain.model.aggregates.Result;
import com.med.supermedstartup.medicalservice.domain.model.commands.CreateResultCommand;
import com.med.supermedstartup.medicalservice.domain.services.ResultCommandService;
import com.med.supermedstartup.medicalservice.infrastructure.persistence.jpa.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultCommandServiceImpl implements ResultCommandService {
    private final ResultRepository resultRepository;

    public ResultCommandServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public Optional<Result> handle(CreateResultCommand command) {
        var result  = new Result(command);
        resultRepository.save(result);
        return Optional.of(result);
    }
}
