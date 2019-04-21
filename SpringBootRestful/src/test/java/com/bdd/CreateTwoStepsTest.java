package com.bdd;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.entity.Customer;
import com.service.ServiceResult;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CreateTwoStepsTest extends AbstractSpringConfigurationTest{
	
	@Autowired
	EntityManager entityManager;
	
	private Customer customer = new Customer();
	
	@Given("^have customer name \"([^\"]*)\" and customer address \"([^\"]*)\"$")
	public void have_customer_name_and_customer_address(String name, String address) throws Throwable {
		customer.setName(name);
		customer.setAddress(address);
		
		String query = "start transaction; begin; savepoint sp1;";
		Query q = entityManager.createNativeQuery(query);
		q.executeUpdate();
	}

	@When("^calls POST \"([^\"]*)\" with the given details$")
	public void calls_POST_with_the_given_details(String path) throws Throwable {
		String url = buildUrl(HOST, PORT, path);
		
		ServiceResult serviceResult = restTemplate.postForObject(url, customer, ServiceResult.class);
		statusCode = serviceResult.getStatus().toString();
	}

	@Then("^status is \"([^\"]*)\"$")
	public void status_is(String status) throws Throwable {
		final String STATUSFAILED = "FAILED";
		if(statusCode.equals(STATUSFAILED)) {
			String query = "rollback to sp1;";
			Query q = entityManager.createNativeQuery(query);
			q.executeUpdate();
		}
		
		assertEquals(status, statusCode);
	}
}
