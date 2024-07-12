package com.qa.apitests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.qa.base.BaseTest;

public class GetAPICallLimehomeTest {
	
	Playwright playwrightAPI;
    APIRequest request;
    APIRequestContext requestContext;

    private static final String BASE_URL = "https://api.limehome.com/properties/v1/public/properties/129";

    @BeforeClass
    public void setUp() {
    	playwrightAPI = Playwright.create();
		request = playwrightAPI.request();
		requestContext = request.newContext();
    }

	@Test
	public void verifyAPIStatusCodeOK() {
		
		APIResponse response = requestContext.get(BASE_URL);
		int statusCode = response.status();
		Assert.assertEquals(statusCode, 200, "Expected status code 200, but got " + statusCode);
	}
	
	@Test
	public void verifyAPIBody() throws IOException {
		
		APIResponse response = requestContext.get(BASE_URL);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonResponse = objectMapper.readTree(response.body());
        
		JsonNode payload = jsonResponse.get("payload");
        Assert.assertNotNull(payload, "Payload is null");
        //Validate property ID
        Assert.assertEquals(payload.get("id").asInt(), 129, "Property ID does not match");

        //Validate property name
        Assert.assertNotNull(payload.get("name").toString(), "Property name is null or empty");

        //Validate property description
        Assert.assertNotNull(payload.get("description").toString(), "Property description is null or empty");

        //Validate property location
        JsonNode location = payload.get("location");
        Assert.assertNotNull(location, "Property location is null or empty");
        Assert.assertNotNull(location.get("city").toString(), "Property city is null or empty");
        Assert.assertNotNull(location.get("postalCode").toString(), "Property city is null or empty");
        Assert.assertNotNull(location.get("countryName").toString(), "Property city is null or empty");
	}
	
	@AfterClass
    public void tearDown() {
		if (playwrightAPI != null) {
			playwrightAPI.close();
        }
    }
	
}
