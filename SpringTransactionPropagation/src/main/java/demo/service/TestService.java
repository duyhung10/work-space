package demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	
	@Transactional
	public Person CreatePersonFullInfo(String name, BigDecimal money) {
		//first step
		long id = personService.createPerson(name);
		System.out.println("First step completed.");
		
		//second step
		walletService.createWalletAttachToPerson(id, money);
		System.out.println("First step completed.");
		
		Person person = personRepository.findById(id).orElse(null);
		return person;
	}
	
	public String getInfoPerson(long id) {
		Person person = personRepository.findById(id).orElse(null);
		
		long walleId = person.getWallet().getId();
		Wallet wallet = walletRepository.findById(walleId).orElse(null);
		
		return "Name: " + person.getName() + ", Wallet's money = " + wallet.getAmount() +".";
	}
}
