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
public class PersonService {

    private final PersonRepository personRepository;
    private final WalletService walletService;
    private final WalletRepository walletRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, WalletService walletService, WalletRepository walletRepository) {
        this.personRepository = personRepository;
        this.walletService = walletService;
        this.walletRepository = walletRepository;
    }

    @Transactional()
    public long createPerson(String name, BigDecimal money) {
        Person person = new Person(name);
        personRepository.save(person);

        Wallet wallet = walletService.createWallet();
        wallet.setAmount(money); // ?!?
        // walletRepository.save(wallet);
        
        person.setWallet(wallet);

        return person.getId();
    }

}
