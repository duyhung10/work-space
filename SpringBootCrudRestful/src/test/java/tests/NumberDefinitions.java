package tests;


import com.example.demo.testcucumber.FizzBuzz;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.jupiter.api.*;


public class NumberDefinitions {
	
	FizzBuzz fizzBuzz;
	String result;
	
	@Given("^Create a FizzBuzz Game play$")
	public void create_a_FizzBuzz_Game_play() throws Throwable {
		fizzBuzz = new FizzBuzz();
	}

	@When("^I play (\\d+)$")
	public void i_play(int number) throws Throwable {
		result = fizzBuzz.play(number);
		System.out.println(number);
		System.out.println(result);
	}

	@Then("^The result is \"([^\"]*)\"$")
	public void the_result_is(String resultString) throws Throwable {
		Assertions.assertEquals(result, resultString );
	}

}
