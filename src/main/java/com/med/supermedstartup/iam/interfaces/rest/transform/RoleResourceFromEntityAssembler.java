package com.med.supermedstartup.iam.interfaces.rest.transform;

import com.med.supermedstartup.iam.domain.model.entities.Role;
import com.med.supermedstartup.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());
    }
}
