package demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.Person;
import demo.service.TestService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceV1Test {

    @Autowired
    private TestService testService;

    @Test
    public void shouldCreatePersonWithEmptyWallet() {
        // when
		testService.CreatePersonAndWallet("Ironman", BigDecimal.valueOf(-82.0D));
    }

}
