package models.queries;

import models.BusinessAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class BusinessAccountQueries extends Queries {

    public BusinessAccountQueries(Connection connection) {
        super((connection));
    }

    public boolean insert_record(int BID,int bankID,int amount) {


        try {

            String query = "INSERT INTO businessAccount (BID,bankID,amount) VALUES (" + BID + ", " + bankID + ", " + amount + ")";
            System.out.println(query);

            query_statement.executeUpdate(query);

        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return false;
    }

	public int getBAID(int BID){
		try {
			String query = "SELECT BAID FROM businessAccount WHERE BID = " + BID;
			System.out.println(query);

			ResultSet resultSet = query_statement.executeQuery(query);
			if(resultSet.next())
				return resultSet.getInt("BAID");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public boolean checkInsert(int BID) {

		return isEmpty("SELECT * FROM businessAccount WHERE BID = " + BID);

	}

	public static int getBalance(int BAID){
		try {
			String query = "SELECT amount FROM businessAccount WHERE BAID = " + BAID;
			ResultSet resultSet = query_statement.executeQuery(query);

			resultSet.next();
			return resultSet.getInt("amount");
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int updateBalance(int BAID,int balance){
		try{
			String query = "UPDATE businessAccount SET amount = " + balance + " WHERE BAID = " + BAID;
			return query_statement.executeUpdate(query);
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
