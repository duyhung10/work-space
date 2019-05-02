package demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import demo.entity.Person;
import demo.entity.Wallet;
import demo.repository.PersonRepository;
import demo.repository.WalletRepository;
import demo.service.WalletService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WalletServiceTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletService walletService;

    @Test
    public void shouldCreateAndAddEmptyWalletToPerson() {
        // given
        Person margaret = personRepository.save(new Person("Margaret"));
        System.out.println("Print console: " + margaret);
        
        // when
        Wallet wallet = walletService.createWalletAndAttachToPerson(margaret);
        long walletId = wallet.getId();
        System.out.println("Print console: " + walletId);

        // then
        Optional<Wallet> dbWallet = walletRepository.findById(walletId);
        Assertions.assertThat(dbWallet).isPresent();
        System.out.println("Print console: " + dbWallet);

        Optional<Person> dbMargaret = personRepository.findById(margaret.getId());
        Assertions.assertThat(dbMargaret).isPresent();
        System.out.println("Print console: " + dbMargaret);

        Wallet margaretWallet = dbMargaret.get().getWallet();
        System.out.println("Print console: " + margaretWallet);
        assertThat(margaretWallet).isNotNull();
        assertThat(margaretWallet.getId()).isNotNull();
        assertThat(margaretWallet.getAmount()).isZero();
    }

}