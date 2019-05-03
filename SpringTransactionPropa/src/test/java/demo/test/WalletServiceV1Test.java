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
public class WalletServiceV1Test {

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
        
        // when
        Wallet wallet = walletService.createWalletAndAttachToPersonV1(margaret);
        long walletId = wallet.getId();

        // then
        Optional<Wallet> dbWallet = walletRepository.findById(walletId);
        Assertions.assertThat(dbWallet).isPresent();

        Optional<Person> dbMargaret = personRepository.findById(margaret.getId());
        Assertions.assertThat(dbMargaret).isPresent();

        Wallet margaretWallet = dbMargaret.get().getWallet();
        assertThat(margaretWallet).isNotNull();
        assertThat(margaretWallet.getId()).isNotNull();
        assertThat(margaretWallet.getAmount()).isZero();
    }

}
