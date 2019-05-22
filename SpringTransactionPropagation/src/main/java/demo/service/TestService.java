package demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Person;
import demo.entity.Wallet;
import demo.repository.PersonRepository;
import demo.repository.WalletRepository;

@Service
public class TestService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private PersonService personService;
	@Autowired
	private WalletService walletService;
	
	
//	@Transactional()
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void CreatePersonAndWallet(String name, BigDecimal money) throws RuntimeException {
		//first step
		Person person = personService.createPerson(name);
		System.out.println("Person's name: " + person.getName());
		System.out.println("First step completed.");
		System.out.println("---------------");
		
		//second step
		Wallet wallet = walletService.createWallet(money);
		System.out.println("Wallet's amount: " + wallet.getAmount());
		System.out.println("Second step completed.");
		
	}
	
}
