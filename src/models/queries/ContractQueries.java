package models.queries;

import java.sql.Connection;
import java.sql.SQLException;

public class ContractQueries extends Queries {

	public ContractQueries(Connection connection){
		super(connection);
	}

	public boolean insert_record(int contractorID, int contracteeID, String title, String terms, int amount) {

		String query = "INSERT INTO contract (contractorID,contracteeID,title,terms,amount) VALUES (" + contractorID + ", " + contracteeID + ", '" + title + "', '" + terms +"', " + amount +  ")";		System.out.println(query);

		try {

			query_statement.executeUpdate(query);

			return true;


		} catch(SQLException exception) {

			exception.printStackTrace();
			System.out.println("Exception in BankQueries..");

			return false;
		}

	}


}
