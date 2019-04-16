package models.queries;

import com.knights.ktmini.DatabaseClasses.Business.BusinessLogin;

import java.sql.Connection;
import java.sql.SQLException;

public class BusinessDepositQueries extends Queries{

	public BusinessDepositQueries(Connection connection) {
		super(connection);
	}

	public boolean insert_record(int BAID,int amt,int UID) {

		String query = "INSERT INTO businessDeposit (BAID,amount,UIN) VALUES (" + BAID + "," + amt + "," + UID + ")";
		System.out.println(query);

		try {
			query_statement.executeUpdate(query);
			System.out.println("Successfully inserted Loan record..");
			int bal = BusinessAccountQueries.getBalance(BAID);
			UserAccountQueries.updateBalance(BAID,bal + amt);
			return true;

		} catch(SQLException exception) {
			exception.printStackTrace();

			return false;
		}
	}
}
