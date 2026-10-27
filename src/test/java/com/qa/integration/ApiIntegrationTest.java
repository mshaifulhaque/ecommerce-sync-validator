package com.qa.integration;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiIntegrationTest {

    @Test
    public void testExternalApiDataRetrieval() {
        // 1. Set the base URI for our testing endpoint (simulating an external ERP or Shopify endpoint)
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // 2. Send a GET request to fetch resource data (e.g., simulating fetching order #1 details)
        Response response = RestAssured.given()
                .when()
                .get("/posts/1")
                .then()
                .extract().response();

        // 3. Print the response JSON to the console log for visibility
        System.out.println("API Response Body:\n" + response.asString());

        // 4. Assertions to validate the API connection and data integrity
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "API endpoint did not return a successful status code!");

        // Validate that the returned JSON contains the correct expected ID
        int postId = response.jsonPath().getInt("id");
        Assert.assertEquals(postId, 1, "Returned post ID does not match expected integration key!");
    }
}