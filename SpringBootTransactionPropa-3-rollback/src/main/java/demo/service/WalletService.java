package demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Wallet;
import demo.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    
    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Wallet createWallet(BigDecimal money) {
        Wallet emptyWallet = new Wallet(money);
        return walletRepository.save(emptyWallet);
    }

}
