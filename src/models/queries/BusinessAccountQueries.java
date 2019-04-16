package models.queries;

import models.BusinessAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class BusinessAccountQueries extends Queries {

	public static int[] BAIDList;
	public static int[] balances;

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

	public String[] getAllBusinessAccounts(int BAID){


		try {
			String query = "SELECT * FROM business NATURAL JOIN businessAccount";
			System.out.println(query);
			ResultSet rs = query_statement.executeQuery(query);

			System.out.println(rs.toString());

			int[] BAIDs = new int[100];
			int[] amount = new int[100];
			String[] Names = new String[100];

			int i = 0;

			while (true) {
				if(rs.next()) {
					System.out.println(rs.getInt("BAID"));
					BAIDs[i] = rs.getInt("BAID");
					Names[i] = rs.getString("name");
					amount[i] = rs.getInt("amount");
					++i;
				}
				else{
					break;
				}
			}

			String[] ua = new String[i];
			int[] finalBAIDS = new int[i];
			int[] finalBalances = new int[i];

			for(int j = 0;j<i;j++){
				if(BAIDs[j] == BAID){
					continue;
				}
				else{
					ua[j] = Names[j];
					finalBAIDS[j] = BAIDs[j];
					finalBalances[j] = amount[j];
				}
			}
			BAIDList = finalBAIDS;
			balances = finalBalances;
			return ua;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
