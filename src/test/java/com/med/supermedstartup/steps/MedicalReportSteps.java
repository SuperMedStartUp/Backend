package com.med.supermedstartup.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

public class MedicalReportSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private Response response;

    @Given("the system is available")
    public void the_system_is_available() {
        baseURI = "http://localhost:8080";

    }

    @When("send a request to create a report with the data:")
    public void send_a_request_to_create_a_report_with_the_data(io.cucumber.datatable.DataTable table) {
        Map<String, String> dataOriginal = table.asMaps().get(0);
        Map<String, String> data = new HashMap<>(dataOriginal);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        response = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJNYXJpbyIsImlhdCI6MTc0OTM1MjU2OCwiZXhwIjoxNzQ5OTU3MzY4fQ.qNjHzWSa5CTRwXDszRc6J7hWQhWMdk2bRBsBBH070gUxrD61oEUl9_gxD3mSJ3s5")
                .body("{"
                        + "\"reason\": \"" + data.get("reason") + "\","
                        + "\"date\": \"" + data.get("date") + "\","
                        + "\"patientId\": " + data.get("patientId")
                        + "}")
                .when()
                .post("/api/v1/reports");

    }

    @Then("the system should respond with a {int} code")
    public void the_system_should_respond_with_a_code(int statusCode) {
        try {
            response.then().statusCode(statusCode);
        } catch (AssertionError e) {
            if (response.statusCode() == 401) {
                System.err.println("Error 401: No autorizado. Verifica el token o los permisos.");
            }
            throw e;
        }
    }

    @And("the report should contain the data:")
    public void the_report_should_contain_the_data(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps(String.class, String.class).get(0);
        response.then()
                .body("reason", equalTo(data.get("reason")))
                .body("date", equalTo(data.get("date")))
                .body("patientId", equalTo(Integer.parseInt(data.get("patientId"))));
    }
}
