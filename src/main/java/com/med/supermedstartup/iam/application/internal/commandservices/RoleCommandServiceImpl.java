package com.med.supermedstartup.iam.application.internal.commandservices;

import com.med.supermedstartup.iam.domain.model.commands.SeedRolesCommand;
import com.med.supermedstartup.iam.domain.model.entities.Role;
import com.med.supermedstartup.iam.domain.model.valueobjects.Roles;
import com.med.supermedstartup.iam.domain.services.RoleCommandService;
import com.med.supermedstartup.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
