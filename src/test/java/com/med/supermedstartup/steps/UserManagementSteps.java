package com.med.supermedstartup.steps;

import com.med.supermedstartup.iam.domain.model.aggregates.User;
import com.med.supermedstartup.iam.domain.model.commands.SignUpCommand;
import com.med.supermedstartup.iam.domain.model.entities.Role;
import com.med.supermedstartup.iam.domain.model.queries.GetAllUsersQuery;
import com.med.supermedstartup.iam.domain.model.queries.GetUserByUsernameQuery;
import com.med.supermedstartup.iam.domain.model.valueobjects.Roles;
import com.med.supermedstartup.iam.domain.services.UserCommandService;
import com.med.supermedstartup.iam.domain.services.UserQueryService;
import com.med.supermedstartup.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.med.supermedstartup.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.an.Y;
import io.cucumber.java.ast.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ContextConfiguration(classes = {CucumberSpringConfiguration.class})
@ComponentScan(basePackages = "com.med.supermedstartup.iam")
public class UserManagementSteps {

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String username;
    private String password;
    private String role;
    private Exception exception;
    private User createdUser;
    private List<User> users;


    @Before
    public void setup() {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(role));
            }
        });
    }

    @Dado("que el sistema está en funcionamiento")
    public void queElSistemaEstaEnFuncionamiento() {
        assertNotNull(userCommandService);
        assertNotNull(userQueryService);
    }

    @Y("que los roles están configurados")
    public void queLosRolesEstanConfigurados() {
        assertTrue(roleRepository.existsByName(Roles.DOCTOR));
        assertTrue(roleRepository.existsByName(Roles.PATIENT));
    }

    @Cuando("creo un usuario con username {string} y password {string} y rol {string}")
    public void creoUnUsuarioConUsernameYPasswordYRol(String username, String password, String role) {

        Role roleEntity = roleRepository.findByName(Roles.valueOf(role)).orElseThrow();

        this.username = username;
        this.password = password;
        this.role = role;
        SignUpCommand command = new SignUpCommand(username, password, roleEntity);
        Optional<User> result = userCommandService.handle(command);
        assertTrue(result.isPresent());
        this.createdUser = result.get();
    }

    @Entonces("el usuario debe ser creado exitosamente")
    public void elUsuarioDebeSerCreadoExitosamente() {
        assertNotNull(createdUser);
        assertEquals(username, createdUser.getUsername());
        assertTrue(passwordEncoder.matches(password, createdUser.getPassword()));
    }

    @Y("el usuario debe tener el rol {string}")
    public void elUsuarioDebeTenerElRol(String role) {
        assertEquals(Roles.valueOf(role), createdUser.getRole().getName());
    }


    @Dado("que existe un usuario con username {string}")
    public void queExisteUnUsuarioConUsername(String username) {
        Role role = roleRepository.findByName(Roles.DOCTOR).get();
        User user = new User(username, "password1234", role);
        userRepository.save(user);
    }

    @Cuando("intento crear un usuario con username {string} y password {string} y rol {string}")
    public void intentoCrearUnUsuarioConUsernameYPasswordYRol(String username, String password, String role) {

        Role roleEntity = roleRepository.findByName(Roles.valueOf(role)).orElseThrow();
        try {
            SignUpCommand command = new SignUpCommand(username, password, roleEntity);
            userCommandService.handle(command);
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Entonces("debe mostrarse un error indicando que el username ya existe")
    public void debeMostrarseUnErrorIndicandoQueElUsernameYaExiste() {
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Username already exists"));
    }


    @Cuando("busco el usuario con username {string}")
    public void buscoElUsuarioConUsername(String username) {
        this.username = username;
        Optional<User> result = userQueryService.handle(new GetUserByUsernameQuery(username));
        assertTrue(result.isPresent());
        this.createdUser = result.get();
    }

    @Entonces("debo obtener los datos del usuario")
    public void deboObtenerLosDatosDelUsuario() {
        assertNotNull(createdUser);
        assertEquals(username, createdUser.getUsername());
    }



    @Dado("que existen usuarios en el sistema")
    public void queExistenUsuariosEnElSistema() {
        Role doctorRole = roleRepository.findByName(Roles.DOCTOR).get();
        Role patientRole = roleRepository.findByName(Roles.PATIENT).get();

        User doctor = new User("doctor4", "pass123", doctorRole);
        User patient = new User("patient2", "pass456", patientRole);

        userRepository.save(doctor);
        userRepository.save(patient);
    }

    @Cuando("solicito la lista de usuarios")
    public void solicitoLaListaDeUsuarios() {
        this.users = userQueryService.handle(new GetAllUsersQuery());
    }

    @Entonces("debo obtener la lista completa de usuarios")
    public void deboObtenerLaListaCompletaDeUsuarios() {
        assertNotNull(users);
        assertTrue(users.size() >= 2);
    }

}
