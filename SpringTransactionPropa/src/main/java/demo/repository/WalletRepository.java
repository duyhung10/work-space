package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.entity.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
