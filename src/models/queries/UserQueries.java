package models.queries;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueries extends Queries {

    public UserQueries(Connection connection) {
        super(connection);
	}

    public Boolean insert_record(User user) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String middleName = user.getMiddleName();
        String password = user.getPassword();

        String contact = user.getContactNumber();

        try {

            // stringValue return a string with quotation marks. This is required for SQL insert

            String query = "insert Into users(firstname,middlename,lastname,contact,password) Values('" + firstName + "', '" + middleName + "', '" + lastName + "', " + contact + ", '" + password + "')";
            System.out.println(query);

            query_statement.executeUpdate(query);
            System.out.println("User inserted into records");

            return true;

        } catch(SQLException exception) {
            exception.printStackTrace();
            return false;
        }


    }

    public boolean checkInsert(String username,String password) {

    	return isEmpty("SELECT * FROM users WHERE firstname = '" + username + "' AND password= '" + password + "'");

    }

    public static boolean userExists(int UIN){
    	return isEmpty("SELECT * FROM users WHERE UIN = " + UIN);
	}

    public int getMainUserID(User user){
    	try {
			String query = "SELECT UIN FROM users WHERE firstname = '" + user.getFirstName() + "' AND password = '" + user.getPassword() + "'";
			System.out.println(query);
			ResultSet resultSet = query_statement.executeQuery(query);

			int ret;

			resultSet.next();
			return resultSet.getInt("UIN");

		}
    	catch(Exception e){
    		e.printStackTrace();
    		return -1;
		}
    }

    private void print_relation(ResultSet resultSet) {

        try {
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(3) + " " + resultSet.getString(4));

            }
        } catch(Exception exception) {
            System.out.println((exception));
        }

    }


}
