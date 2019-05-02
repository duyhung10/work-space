package demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.model.BankAccount;

public class BankAccountMapper implements RowMapper<BankAccount> {

	public static final String BASE_SQL = "Select ba.id, ba.full_name, ba.balance From bank_account ba ";

	@Override
	public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("Id");
		String fullName = rs.getString("Full_Name");
		double balance = rs.getDouble("Balance");

		return new BankAccount(id, fullName, balance);
	}

}
