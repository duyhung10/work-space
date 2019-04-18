package com.bdd;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.entity.Customer;
import com.service.CustomerService;
import com.service.ServiceResult;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateCustomerTest extends AbstractSpringConfigurationTest {
	
	@Autowired
	private CustomerService customerService;
	
	private Customer customer = new Customer();
	private String customerName = null;
	private String customerAddress = null;
	
	@Given("^the customer with customer id is (\\d+)$")
	public void the_customer_with_customer_id_is(int id) throws Throwable {
		customer.setId(id);
	}

	@When("^the customer after update has name is \"([^\"]*)\" and address is \"([^\"]*)\"$")
	public void the_customer_after_update_has_name_is_and_address_is(String name, String address) throws Throwable {
		customer.setName(name);
		customer.setAddress(address);
	}

	@When("^the client calls Put \"([^\"]*)\" with the given details$")
	public void the_client_calls_Put_with_the_given_details(String path) throws Throwable {
		String url = buildUrl(HOST, PORT, path);
		
		// update customer
		restTemplate.put(url, customer);
		
		// get that customer by id (after update)
		ServiceResult serviceResult = customerService.findById(customer.getId());
		
		statusCode = serviceResult.getStatus().toString();
		customerName = serviceResult.getData().get(0).getName();
		customerAddress = serviceResult.getData().get(0).getAddress();
		
		System.out.println(statusCode +"/" + customerName + "/" + customerAddress);
	}
	
	@Then("^result status is \"([^\"]*)\"$")
	public void result_status_is(String status) throws Throwable {
		assertEquals(status, statusCode);
	}

	@Then("^result customer name \"([^\"]*)\"$")
	public void result_customer_name(String name) throws Throwable {
		assertEquals(name, customerName);
	}

	@Then("^result customer address \"([^\"]*)\"$")
	public void result_customer_address(String address) throws Throwable {
		assertEquals(address, customerAddress);
	}

}
