package com.med.supermedstartup.iam.domain.model.entities;

import com.med.supermedstartup.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Roles name;

    public Role() {}

    public Role(Roles name) {
        this.name = name;
    }


    public Roles getName() {
        return name;
    }
    public static Role toRoleFromName(String name) { return new Role(Roles.valueOf(name)); }
    public String getStringName(){ return name.name();}
}
