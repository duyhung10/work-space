package com.bdd;

import static org.junit.Assert.assertEquals;

import org.springframework.web.client.RestTemplate;

import com.service.ServiceResult;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetCustomerDefinitionTest  extends AbstractSpringConfigurationTest {
	
	RestTemplate restTemplate = new RestTemplate();
	String statusCode;
	
	@Given("^the client in localhost$")
	public void the_client_in_localhost() throws Throwable {
		System.out.println("Client in localhost");
	}

	@When("^the client chosee get all Customer$")
	public void the_client_chosee_get_all_Customer() throws Throwable {
		String url = buildUrl(HOST, PORT, "/api/customers");
		System.out.println("Client get all Customer");
		
		ServiceResult serviceResult = restTemplate.getForObject(url, ServiceResult.class);
		statusCode = serviceResult.getStatus().toString();
	}
	
	@Then("^the result status is \"([^\"]*)\"$")
	public void the_result_status_is(String status) throws Throwable {
		assertEquals(status, statusCode);
	}

}
