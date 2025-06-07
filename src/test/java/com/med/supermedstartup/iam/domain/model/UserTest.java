package com.med.supermedstartup.iam.domain.model;

import com.med.supermedstartup.iam.domain.model.aggregates.User;
import com.med.supermedstartup.iam.domain.model.entities.Role;
import com.med.supermedstartup.iam.domain.model.valueobjects.Roles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void crearUser() {
        Role role = new Role(Roles.DOCTOR);
        User user = new User("doctor1", "password123", role);
        assertEquals("doctor1", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(Roles.DOCTOR, user.getRole().getName());
    }
}