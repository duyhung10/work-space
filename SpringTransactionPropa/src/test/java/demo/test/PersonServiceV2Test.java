package demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.Person;
import demo.entity.Wallet;
import demo.repository.PersonRepository;
import demo.service.PersonService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceV2Test {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldCreatePersonWithEmptyWallet() {
        // when
        long ironmanId = personService.createPersonV2("IronMan");
        
        // then
        Optional<Person> ironman = personRepository.findById(ironmanId);
        assertThat(ironman).isPresent();
        
        Wallet ironmanWallet = ironman.get().getWallet();
        assertThat(ironmanWallet.getId()).isNotNull();
        assertThat(ironmanWallet.getAmount()).isZero();
    }

}