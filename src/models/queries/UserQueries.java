package models.queries;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueries extends Queries {

	public static int userID = 1901;

    public UserQueries(Connection connection) {
        super(connection);
	}

    //UserQueries() {}

    public Boolean insert_record(User user) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String middleName = user.getMiddleName();
        String password = user.getPassword();

        userID++;
        String contact = user.getContactNumber();

        try {

            // stringValue return a string with quotation marks. This is required for SQL insert

            String query = "insert Into users Values(" + userID + ", '" + firstName + "', '" + middleName + "', '" + lastName + "', '" + contact + "', '" + password + "')";
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

        //try {

            return isEmpty("SELECT * FROM users WHERE firstname = '" + username + "' AND password= '" + password + "'");
        //    System.out.println("Queried data..");

         //   return !result.next();

        //} catch (Exception exception) {
        //    System.out.println(exception);
        //    return true;
        //}

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
