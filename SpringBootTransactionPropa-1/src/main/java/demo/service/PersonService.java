package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Person;
import demo.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final WalletService walletService;

    @Autowired
    public PersonService(PersonRepository personRepository, WalletService walletService) {
        this.personRepository = personRepository;
        this.walletService = walletService;
    }

    @Transactional()
    public long createPerson(String name) {
        Person person = new Person(name);
        personRepository.save(person);

        walletService.createWalletAndAttachToPerson(person.getId());

        return person.getId();
    }

}
