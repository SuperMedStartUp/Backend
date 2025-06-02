package com.med.supermedstartup.iam.domain.services;

import com.med.supermedstartup.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
