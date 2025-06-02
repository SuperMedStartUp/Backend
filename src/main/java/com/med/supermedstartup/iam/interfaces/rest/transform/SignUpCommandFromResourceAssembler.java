package com.med.supermedstartup.iam.interfaces.rest.transform;

import com.med.supermedstartup.iam.domain.model.commands.SignUpCommand;
import com.med.supermedstartup.iam.domain.model.entities.Role;
import com.med.supermedstartup.iam.domain.model.valueobjects.Roles;
import com.med.supermedstartup.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var erole = new Role(Roles.valueOf(resource.role()));
        return new SignUpCommand(resource.username(), resource.password(), erole);
    }
}
