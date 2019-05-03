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
public class WalletServiceV2Test {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletService walletService;

    @Test
    public void shouldCreateAndAddEmptyWalletToPerson() {
        // given
        Person clim = personRepository.save(new Person("Clim"));
        
        // when
        long walletId = walletService.createWalletAndAttachToPersonV2(clim.getId()).getId();
        
        // then
        Optional<Wallet> dbWallet = walletRepository.findById(walletId);
        Assertions.assertThat(dbWallet).isPresent();

        Optional<Person> dbClim = personRepository.findById(clim.getId());
        Assertions.assertThat(dbClim).isPresent();

        Wallet climWallet = dbClim.get().getWallet();
        assertThat(climWallet).isNotNull();
        assertThat(climWallet.getId()).isNotNull();
        assertThat(climWallet.getAmount()).isZero();
    }

}
