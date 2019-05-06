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
public class WalletService {

	private final WalletRepository walletRepository;
	private final PersonRepository personRepository;

	@Autowired
	public WalletService(WalletRepository walletRepository, PersonRepository personRepository) {
		this.walletRepository = walletRepository;
		this.personRepository = personRepository;
	}
	
	@Transactional
	public Wallet createWalletAttachToPerson(long personId, BigDecimal money) {
		Wallet wallet = new Wallet(money);
		walletRepository.save(wallet);
		
		Wallet walletDb = walletRepository.findById(wallet.getId()).orElse(null);
		if (walletDb.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("Initial amount of money cannot be less than zero");
		}
		
		Person person = personRepository.findById(personId).orElseThrow(null);
		person.setWallet(wallet);

		return wallet;
	}

}
