package com.med.supermedstartup.profiles.interfaces.rest.resources;

public record PatientResource(Long id, String fullName, String email, String phone, String streetAddress) {
}
