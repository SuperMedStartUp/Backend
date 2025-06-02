package com.med.supermedstartup.iam.domain.services;

import com.med.supermedstartup.iam.domain.model.entities.Role;
import com.med.supermedstartup.iam.domain.model.queries.GetAllRolesQuery;
import com.med.supermedstartup.iam.domain.model.queries.GetRoleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByIdQuery query);
}
