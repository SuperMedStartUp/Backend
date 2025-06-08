package com.med.supermedstartup.iam.application.service;

import com.med.supermedstartup.iam.application.internal.commandservices.UserCommandServiceImpl;
import com.med.supermedstartup.iam.application.internal.outboundservices.hashing.HashingService;
import com.med.supermedstartup.iam.application.internal.outboundservices.tokens.TokenService;
import com.med.supermedstartup.iam.domain.model.aggregates.User;
import com.med.supermedstartup.iam.domain.model.commands.SignInCommand;
import com.med.supermedstartup.iam.domain.services.UserCommandService;
import com.med.supermedstartup.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.med.supermedstartup.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserCommandServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private HashingService hashingService;

    @Mock
    private TokenService tokenService;

    private UserCommandService userCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userCommandService = new UserCommandServiceImpl(
                userRepository, hashingService, tokenService, roleRepository
        );
    }

    @Test
    void iniciarSesionExitosamente() {
        SignInCommand command = new SignInCommand("Rolando", "rolando");
        User user = new User();
        user.setUsername(command.username());
        user.setPassword(command.password());

        when(hashingService.matches(anyString(), anyString())).thenReturn(true);
        when(tokenService.generateToken(anyString())).thenReturn("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJSb2xhbmRvIiwiaWF0IjoxNzQ4OTkzNzE3LCJleHAiOjE3NDk1OTg1MTd9.1tkh_4d1qeE5SSv0UJGm2a46dxIPtNUF9lUcozkmJrhP_f616iiwuqQlwyhKMFIW");
        when(userRepository.existsByUsername(command.username())).thenReturn(false);
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        Optional<ImmutablePair<User, String>> result = userCommandService.handle(command);

    }

}
