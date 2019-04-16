package sbdatajpa.bdd;

import static org.junit.Assert.assertEquals;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetEmployeeStepDefinitionTest extends AbstractSpringConfigurationTest {
	
	private ResponseEntity<String> response=null;
	
	@Given("^the client in localhost$")
	public void the_client_in_localhost() throws Throwable {
		String url = buildUrl(HOST, PORT, "");
		response = invokeRESTCall(url, HttpMethod.GET, null);
	}

	@When("^the client click Show All Employee$")
	public void the_client_click_Show_All_Employee() throws Throwable {
		String url = buildUrl(HOST, PORT, "/showAllEmployee");
		response = invokeRESTCall(url, HttpMethod.GET, null);
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
		assertEquals(statusCode, response.getStatusCode().value());
	}
}
