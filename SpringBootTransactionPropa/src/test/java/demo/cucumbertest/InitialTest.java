package demo.cucumbertest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import demo.entity.Person;
import demo.entity.Wallet;
import demo.repository.PersonRepository;
import demo.service.PersonService;

public class InitialTest {

	@Autowired
	private PersonService personService;
	@Autowired
	private PersonRepository personRepository;

	Optional<Person> peter;
	long peterId;
	Wallet peterWallet;

	@Given("^Create  a new person$")
	public void create_a_new_person() throws Throwable {
		peterId = personService.createPerson("Peter");
	}

	@When("^I check Is person present$")
	public void i_check_Is_person_present() throws Throwable {
		peter = personRepository.findById(peterId);
		assertThat(peter).isPresent();
	}

	@Then("^I check Is person's wallet exist$")
	public void i_check_Is_person_s_wallet_exist() throws Throwable {
		peterWallet = peter.get().getWallet();
		assertThat(peterWallet.getId()).isNotNull();
	}

	@Then("^I check Is person's wallet amount$")
	public void i_check_Is_person_s_wallet_amount() throws Throwable {
		assertThat(peterWallet.getAmount()).isZero();
	}
}
