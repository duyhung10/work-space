package com.bdd;

import static org.junit.Assert.assertEquals;

import com.entity.Customer;
import com.service.ServiceResult;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateCustomerTest extends AbstractSpringConfigurationTest {

	private Customer customer = new Customer();
	private String customerName = null;
	private String customerAddress = null;
	
	@Given("^the customer with customer name \"([^\"]*)\" and customer address \"([^\"]*)\"$")
	public void the_customer_with_customer_name_and_customer_address(String name, String address) throws Throwable {
		customer.setName(name);
		customer.setAddress(address);
	}

	@When("^the client calls POST \"([^\"]*)\" with the given details$")
	public void the_client_calls_POST_with_the_given_details(String path) throws Throwable {
		String url = buildUrl(HOST, PORT, path);
		
		ServiceResult serviceResult = restTemplate.postForObject(url, customer, ServiceResult.class);
		

		statusCode = serviceResult.getStatus().toString();
		customerName = serviceResult.getData().get(0).getName();
		customerAddress = serviceResult.getData().get(0).getAddress();
	}

	@Then("^the client receives result status is \"([^\"]*)\"$")
	public void the_client_receives_result_status_is(String status) throws Throwable {
		assertEquals(status, statusCode);
	}

	@Then("^response customer name \"([^\"]*)\"$")
	public void response_customer_name(String name) throws Throwable {
		assertEquals(name, customerName);
	}

	@Then("^response customer address \"([^\"]*)\"$")
	public void response_customer_address(String address) throws Throwable {
		assertEquals(address, customerAddress);
	}
}
