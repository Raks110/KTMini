package models.queries;

import models.Bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankQueries extends Queries {

	public static int immediateLength;

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
			immediateLength = 0;

			System.out.println("Inside getAllBanks");

			while(resultSet.next()){
				System.out.println(resultSet.getString("name"));
				ret[i] = resultSet.getString("name");
				System.out.println(ret[i]);
				++i;
				++immediateLength;
			}

			List<String> list = new ArrayList<String>();

			for(String s : ret) {
				if(s != null && s.length() > 0) {
					list.add(s);
				}
			}

			ret = list.toArray(new String[list.size()]);

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

	public int getMainbankID(int managerID,String password){
		try {
			String query = "SELECT bankID FROM bank WHERE managerID = " + managerID + " AND password = '" + password + "'";
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("bankID");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
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

	public static int getNumUsers(int bankID){
		try {
			String query = "SELECT COUNT(*) FROM user_account WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("COUNT(*)");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int getNumLoan(int bankID){
		try {
			String query = "SELECT COUNT(*) FROM loan NATURAL JOIN user_account WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("COUNT(*)");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int getNumDeposits(int bankID){
		try {
			String query = "SELECT COUNT(*) FROM deposit NATURAL JOIN user_account WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("COUNT(*)");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int getBusinessNumUsers(int bankID){
		try {
			String query = "SELECT COUNT(*) FROM businessAccount WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("COUNT(*)");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int getBusinessNumLoan(int bankID){
		try {
			String query = "SELECT COUNT(*) FROM businessLoan NATURAL JOIN businessAccount WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("COUNT(*)");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static int getBusinessNumDeposits(int bankID){
		try {
			String query = "SELECT COUNT(*) FROM businessDeposit NATURAL JOIN businessAccount WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getInt("COUNT(*)");
			else
				return -1;

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public String getBankName(int bankID){
		try {
			String query = "SELECT name FROM bank WHERE bankID = " + bankID;
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			int ret;

			if(resultSet.next())
				return resultSet.getString("name");
			else
				return "NA";

		}
		catch(Exception e){
			e.printStackTrace();
			return "NA";
		}
	}

}
