package models.queries;

import java.nio.charset.IllegalCharsetNameException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanQueries extends Queries {

	public LoanQueries(Connection connection) {
		super(connection);
	}

	public boolean insert_record(int accn,int amt,int UID) {

		String query = "INSERT INTO loan (accountID,amount,UIN) VALUES (" + Integer.toString(accn) + "," + Integer.toString(amt) + "," + Integer.toString(UID) + ")";
		System.out.println(query);

		try {
			query_statement.executeUpdate(query);
			System.out.println("Successfully inserted Loan record..");
			return true;

		} catch(SQLException exception) {
			exception.printStackTrace();

			return false;
		}
	}

	public boolean loanTaken(int UIN){
		String query = "SELECT * FROM loan WHERE UIN = " + Integer.toString(UIN);
		try {
			ResultSet resultSet = query_statement.executeQuery(query);
			if(resultSet.next())
				return true;
			else
				return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}

	public int loanAmt(int UIN){
		String query = "SELECT amount FROM loan WHERE UIN = " + Integer.toString(UIN);
		try {
			ResultSet resultSet = query_statement.executeQuery(query);
			resultSet.next();
			return resultSet.getInt("amount");
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int updateLoanAmt(int UIN,int amt){
		try{
			if(amt <= 0){
				String query = "DELETE FROM loan WHERE UIN = " + Integer.toString(UIN);
				return query_statement.executeUpdate(query);
			}
			else {
				String query = "UPDATE loan SET amount = " + Integer.toString(amt) + " WHERE UIN = " + Integer.toString(UIN);
				return query_statement.executeUpdate(query);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
