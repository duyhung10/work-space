package demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    public Wallet() {
        this.amount = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
