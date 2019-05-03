package demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
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
public class PersonServiceV3Test {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldCreatePersonWithEmptyWallet() {
        // when
        long vinId = personService.createPersonV3("Vin", BigDecimal.TEN);
        
        // then
        Optional<Person> vin = personRepository.findById(vinId);
        assertThat(vin).isPresent();
        
        Wallet vinWallet = vin.get().getWallet();
        assertThat(vinWallet.getId()).isNotNull();
        assertThat(vinWallet.getAmount()).isZero();
    }

}
