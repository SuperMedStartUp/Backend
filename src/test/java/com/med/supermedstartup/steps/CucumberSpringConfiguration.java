package com.med.supermedstartup.steps;

import com.med.supermedstartup.BackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@CucumberContextConfiguration
@SpringBootTest(
        classes = BackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ComponentScan(basePackages = {
        "com.med.supermedstartup.iam",
        "com.med.supermedstartup.medicalservice",
        "com.med.supermedstartup.shared"
})
public class CucumberSpringConfiguration {
}