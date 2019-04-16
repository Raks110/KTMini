package models.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessLoanQueries extends Queries{

	public BusinessLoanQueries(Connection connection) {
		super(connection);
	}

	public boolean insert_record(int BAID,int amt,int UID) {

		String query = "INSERT INTO businessLoan (BAID,amount,UIN) VALUES (" + BAID + "," + amt + "," + UID + ")";
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

	public boolean loanTaken(int BAID){
		String query = "SELECT * FROM businessLoan WHERE BAID = " + BAID;
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

	public int loanAmt(int BAID){
		String query = "SELECT amount FROM businessLoan WHERE BAID = " + BAID;
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

	public static int updateLoanAmt(int BAID,int amt){
		try{
			if(amt <= 0){
				String query = "DELETE FROM businessLoan WHERE BAID = " + BAID;
				return query_statement.executeUpdate(query);
			}
			else {
				String query = "UPDATE loan SET amount = " + amt + " WHERE BAID = " + BAID;
				return query_statement.executeUpdate(query);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
