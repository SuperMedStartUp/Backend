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

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Response response;



    @Given("the system is available")
    public void the_system_is_available() {
        baseURI = "http://localhost:" + port;



    }

    @When("send a request to create a report with the data:")
    public void send_a_request_to_create_a_report_with_the_data(io.cucumber.datatable.DataTable table) {
        Map<String, String> dataOriginal = table.asMaps().get(0);
        Map<String, String> data = new HashMap<>(dataOriginal);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        response = given()
                .baseUri("http://localhost:" + port)
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJSb2xhbmRvIiwiaWF0IjoxNzQ4OTIyNzk2LCJleHAiOjE3NDk1Mjc1OTZ9.pfSRclBNl7vyI0-fuz2vKr0qQoU39gl5nHCa4kVTb53vrM6Dmcm88Jo_8ZesYNWi")
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
        response.then().statusCode(statusCode);
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
