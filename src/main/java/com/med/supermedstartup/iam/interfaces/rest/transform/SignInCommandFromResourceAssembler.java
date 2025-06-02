package com.med.supermedstartup.iam.interfaces.rest.transform;

import com.med.supermedstartup.iam.domain.model.commands.SignInCommand;
import com.med.supermedstartup.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
