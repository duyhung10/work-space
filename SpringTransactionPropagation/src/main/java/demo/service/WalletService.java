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

	@Autowired
	public WalletService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}
	
//	@Transactional(propagation = Propagation.REQUIRED)
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Transactional(propagation = Propagation.MANDATORY)
	@Transactional(propagation = Propagation.NEVER)
//	@Transactional(propagation = Propagation.SUPPORTS)
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Wallet createWallet(BigDecimal money) throws RuntimeException {
		Wallet wallet = new Wallet(money);
		Wallet walletDB = walletRepository.save(wallet);
		System.out.println("Create wallet with amount = " + walletDB.getAmount());
		
		if (wallet.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("Initial amount of money cannot be less than zero");
		}
		
		return wallet;
	}

}
