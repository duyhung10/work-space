package demo.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import demo.repository.WalletRepository;
import demo.service.PersonService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void shouldNotCreateAnythingWhenTryingToCreatePersonWithNegativeAmountOfMoney() {
        // when
        assertThatThrownBy(() -> personService.createPerson("Vince", BigDecimal.valueOf(-100.0D)))
                .isInstanceOf(RuntimeException.class);

        // then
        assertThat(personRepository.findAll()).isEmpty();
        assertThat(walletRepository.findAll()).isEmpty();
    }

}