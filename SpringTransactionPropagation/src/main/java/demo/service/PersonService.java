package demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Person;
import demo.repository.PersonRepository;
import demo.repository.WalletRepository;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public Person createPerson(String name) {
		Person person = new Person(name);
		Person personDB = personRepository.save(person);
		System.out.println("Create Person with name = " + personDB.getName());
		return person;
	}
}
