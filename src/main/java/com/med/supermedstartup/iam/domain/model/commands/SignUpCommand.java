package com.med.supermedstartup.iam.domain.model.commands;

import com.med.supermedstartup.iam.domain.model.entities.Role;

public record SignUpCommand(String username, String password, Role role) {
}
