package demo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.exception.BankTransactionException;
import demo.mapper.BankAccountMapper;
import demo.model.BankAccount;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {
	@Autowired
	public BankAccountDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<BankAccount> getBankAccounts() {
		String sql = BankAccountMapper.BASE_SQL;

		Object[] params = new Object[] {};
		BankAccountMapper mapper = new BankAccountMapper();
		List<BankAccount> list = this.getJdbcTemplate().query(sql, params, mapper);

		return list;
	}

	public BankAccount findBankAccount(Long id) {
		// Select ba.Id, ba.Full_Name, ba.Balance From Bank_Account ba
		// Where ba.Id = ?
		String sql = BankAccountMapper.BASE_SQL + " where ba.id = ? ";

		Object[] params = new Object[] { id };
		BankAccountMapper mapper = new BankAccountMapper();
		try {
			BankAccount bankAccount = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws BankTransactionException {
        BankAccount account = this.findBankAccount(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException("The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
        // Update to DB
        String sqlUpdate = "Update bank_account set balance = ? where id = ?";
        this.getJdbcTemplate().update(sqlUpdate, account.getBalance(), account.getId());
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
