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
public class WalletService {

	private final WalletRepository walletRepository;
	private final PersonRepository personRepository;

	@Autowired
	public WalletService(WalletRepository walletRepository, PersonRepository personRepository) {
		this.walletRepository = walletRepository;
		this.personRepository = personRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Wallet createWalletAndAttachToPersonV1(Person person) {
		Wallet emptyWallet = new Wallet();
		walletRepository.save(emptyWallet);

		person.setWallet(emptyWallet);

		return emptyWallet;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Wallet createWalletAndAttachToPersonV2(long personId) {
		Wallet emptyWallet = new Wallet();
		walletRepository.save(emptyWallet);

		Person person = personRepository.findById(personId).orElseThrow(null);
		person.setWallet(emptyWallet);

		return emptyWallet;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Wallet createWallet() {
		Wallet emptyWallet = new Wallet();
		return walletRepository.save(emptyWallet);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Transactional(propagation = Propagation.REQUIRED)
	public Wallet createWalletV4(BigDecimal money) {
		Wallet emptyWallet = new Wallet(money);
		return walletRepository.save(emptyWallet);
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public Wallet createWalletV5(BigDecimal money) {
		Wallet emptyWallet = new Wallet(money);
		return walletRepository.save(emptyWallet);
	}
	
	@Transactional(propagation = Propagation.NEVER)
	public Wallet createWalletV7(BigDecimal money) {
		Wallet emptyWallet = new Wallet(money);
		return walletRepository.save(emptyWallet);
	}
}
