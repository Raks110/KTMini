package models.queries;

import com.knights.ktmini.DatabaseClasses.Users.UserLogin;
import models.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountQueries extends Queries {

	public static int[] UIDs;
	public static int[] balances;

    public UserAccountQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(UserAccount userAccount) {

        int accountID = userAccount.getAccountID();
        int userID = userAccount.getUIN();
        int bankID = userAccount.getBankID();

        String query = insertIntoTableValues("user_account") + accountID + ", " + userID + ", " + bankID +  ",0)";

        System.out.println(query);

        try {

            query_statement.executeUpdate(query);

            System.out.println("Successfully inserted UserAccount record..");

            return true;

        } catch(SQLException exception) {
            exception.printStackTrace();

            return false;
        }
    }

    public static int getBalance(int UIN){
    	try {
			String query = "SELECT balance FROM user_account WHERE UIN = " + Integer.toString(UIN);
			ResultSet resultSet = query_statement.executeQuery(query);

			resultSet.next();
			return resultSet.getInt("balance");
		}
    	catch(Exception e){
    		e.printStackTrace();
    		return -1;
		}
	}

	public static int updateBalance(int UIN,int balance){
    	try{
    		String query = "UPDATE user_account SET balance = " + Integer.toString(balance) + " WHERE UIN = " + Integer.toString(UIN);
    		return query_statement.executeUpdate(query);
		}
    	catch(Exception e){
    		e.printStackTrace();
    		return 0;
		}
	}

	public static int getAccountNum(int UIN){
		try {
			String query = "SELECT accountID FROM user_account WHERE UIN = " + Integer.toString(UIN);
			ResultSet resultSet = query_statement.executeQuery(query);

			resultSet.next();
			return resultSet.getInt("accountID");
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public static String getName(int UIN){
		try {
			String query = "SELECT firstname FROM users WHERE UIN = " + Integer.toString(UIN);
			ResultSet resultSet = query_statement.executeQuery(query);

			if(resultSet.next())
				return resultSet.getString("firstname");
			else
				return "NA";
		}
		catch(Exception e){
			e.printStackTrace();
			return "NA";
		}
	}

	public String[] getAllUserAccounts(int UID){

    	UserAccount[] list = new UserAccount[100];

		try {
			String query = "SELECT * FROM user_account";
			System.out.println(query);
			ResultSet rs = query_statement.executeQuery(query);

			System.out.println(rs.toString());

			int[] UINArr = new int[100];
			int[] AccnArr = new int[100];
			int[] balArr = new int[100];
			int[] bankArr = new int[100];

			int i = 0;
			UserLogin.lenUsers = 0;

			//try {
				while (true) {
					if(rs.next()) {
						System.out.println(rs.getInt("UIN"));
						UINArr[i] = rs.getInt("UIN");
						AccnArr[i] = rs.getInt("accountID");
						balArr[i] = rs.getInt("balance");
						bankArr[i] = rs.getInt("bankID");
						++i;
					}
					else{
						break;
					}
				}

				String[] ua = new String[i];
				for(int j = 0;j<i;j++){
					if(UINArr[j] == UID){
						continue;
					}
					else{
						//System.out.println(AccnArr[j] +  " " +UINArr[j] + " " +bankArr[j]+ " " +balArr[j]);
						//ua[j] = new UserAccount(AccnArr[j],UINArr[j],bankArr[j],balArr[j]);

						ua[j] = UserAccountQueries.getName(UINArr[j]);
					}
				}
			//}
			//catch(Exception e){e.getMessage();}

			UIDs = UINArr;
				balances = balArr;
			return ua;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
