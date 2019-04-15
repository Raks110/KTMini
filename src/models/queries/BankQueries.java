package models.queries;

import models.Bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankQueries extends Queries {

    public BankQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(String name, int UIN, String password) {

        String query = "INSERT INTO bank (name,managerID,password) VALUES ('" + name + "', " + UIN + ", '" + password + "')";

        System.out.println(query);

        try {

            query_statement.executeUpdate(query);

            return true;


        } catch(SQLException exception) {

            exception.printStackTrace();
            System.out.println("Exception in BankQueries..");

            return false;
        }

    }

    public String[] getAllBanks(){
    	try {

    		String[] ret = new String[100];

			String query = "SELECT name FROM bank";
			ResultSet resultSet = query_statement.executeQuery(query);

			int i = 0;

			System.out.println("Inside getAllBanks");

			while(resultSet.next()){
				System.out.println(resultSet.getString("name"));
				ret[i] = resultSet.getString("name");
				System.out.println(ret[i]);
				++i;
			}

			return ret;

		}
    	catch(Exception e){
    		e.printStackTrace();
    		return null;
		}
	}

	public String getMainbankID(String bank){
		try {
			String query = "SELECT bankID FROM bank WHERE name = '" + bank + "'";
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			int ret;

			if(resultSet.next())
				return Integer.toString(resultSet.getInt("bankID"));
			else
				return "NA";

		}
		catch(Exception e){
			e.printStackTrace();
			return "NA";
		}
	}

	public static boolean insert_into_payments(int rUIN,int sUIN,int amount){
    	String query = "INSERT INTO payments VALUES (" + rUIN + ", " + sUIN + ", " + amount + ")";
    	try{
    		query_statement.executeUpdate(query);
    		return true;
		}
    	catch(Exception e){
    		e.printStackTrace();
    		return false;
		}
	}

}
