package com.bdd;

import static org.junit.Assert.assertEquals;

import com.entity.Customer;
import com.service.ServiceResult;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetCustomerByIdDefinitionTest extends AbstractSpringConfigurationTest {

	private String customerName;
	
	@Given("^client in localhost$")
	public void client_in_localhost() throws Throwable {
		System.out.println("Client in localhost");
	}

	@When("^the client calls GET \"([^\"]*)\" with customer id as (\\d+)$")
	public void the_client_calls_GET_with_customer_id_as(String path, int customerId) throws Throwable {
		String pathUri = path + "/" + String.valueOf(customerId);
		
		String url = buildUrl(HOST, PORT, pathUri);
		
		System.out.println("Client get Customer with customer id is " + customerId);
		
		ServiceResult serviceResult = restTemplate.getForObject(url, ServiceResult.class);

		statusCode = serviceResult.getStatus().toString();
		if(serviceResult.getData() != null) {
			Customer c = serviceResult.getData().get(0);
			customerName = c.getName();
		} else {
			customerName = null;
		}
	}

	@Then("^the status is \"([^\"]*)\"$")
	public void the_status_is(String status) throws Throwable {
		assertEquals(status, statusCode);
	}

	@Then("^the response contains customer name \"([^\"]*)\"$")
	public void the_response_contains_customer_name(String name) throws Throwable {
		assertEquals(name, customerName);
	}
}
