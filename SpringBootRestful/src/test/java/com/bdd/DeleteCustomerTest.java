package com.bdd;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.CustomerService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteCustomerTest extends AbstractSpringConfigurationTest {
	
	@Autowired
	CustomerService customerService;
	
	private int customerId;
	
	@Given("^client delete customer with id is (\\d+)$")
	public void client_delete_customer_with_id_is(int id) throws Throwable {
		customerId = id;
	}

	@When("^the client calls Delete \"([^\"]*)\"$")
	public void the_client_calls_Delete(String path) throws Throwable {
		// Check customer id exit
		if(customerService.findById(customerId).getStatus().toString().equals("SUCCESS")) {
			String pathUri = path + "/" + String.valueOf(customerId);
			String url = buildUrl(HOST, PORT, pathUri);
			
			// Delete customer
			restTemplate.delete(url);
			// Check delete customer
			if(customerService.findById(customerId).getStatus().toString().equals("FAILED")) {
				statusCode = "SUCCESS";
			} else {
				statusCode = "FAILED";
			}
		} else {
			statusCode = "FAILED";
			System.out.println("Customer is not exist");
		}
		
		
	}

	@Then("^response status is \"([^\"]*)\"$")
	public void response_status_is(String status) throws Throwable {
		assertEquals(status, statusCode);
	}
}
