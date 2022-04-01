package com.cydeo.step_definitions;

import com.cydeo.utilities.ConfigurationReader;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.lang.module.ResolutionException;

public class AllEnvironmentSteps {


    @When("User sends a request to spartan API using id {int}")
    public void userSendsARequestToSpartanAPIUsingId(int id) {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .get(ConfigurationReader.get("spartan.apiUrl") + "/api/spartans/{id}");

        response.prettyPrint();



    }

}
