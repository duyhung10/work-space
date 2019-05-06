package demo.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import demo.service.PersonService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceV4RollbackTest {
	@Autowired
	private PersonService personService;

	@Test
	public void shouldNotCreateAnythingWhenTryingToCreatePersonWithNegativeAmountOfMoney() {
		// when
		assertThatThrownBy(() -> personService.createPerson("Vince", BigDecimal.valueOf(-100.0D))).isInstanceOf(RuntimeException.class);

//		assertThatThrownBy(() -> personService.createPerson("Huy", BigDecimal.valueOf(80.0D))).isInstanceOf(RuntimeException.class);
	}
}
