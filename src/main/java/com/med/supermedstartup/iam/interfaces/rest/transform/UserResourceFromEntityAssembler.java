package com.med.supermedstartup.iam.interfaces.rest.transform;

import com.med.supermedstartup.iam.domain.model.aggregates.User;
import com.med.supermedstartup.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getUsername(),
                entity.getRole().getStringName());
    }
}
