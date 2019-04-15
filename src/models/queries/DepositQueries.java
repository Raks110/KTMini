package models.queries;

import java.sql.Connection;
import java.sql.SQLException;

public class DepositQueries extends Queries{
	public DepositQueries(Connection connection) {
		super(connection);
	}

	public boolean insert_record(int accn,int amt,int UID) {

		String query = "INSERT INTO deposit (accountID,amount,UIN) VALUES (" + Integer.toString(accn) + "," + Integer.toString(amt) + "," + Integer.toString(UID) + ")";
		System.out.println(query);

		try {
			query_statement.executeUpdate(query);
			System.out.println("Successfully inserted Loan record..");
			int bal = UserAccountQueries.getBalance(UID);
			UserAccountQueries.updateBalance(UID,bal + amt);
			return true;

		} catch(SQLException exception) {
			exception.printStackTrace();

			return false;
		}
	}
}
