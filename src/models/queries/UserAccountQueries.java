package models.queries;

import models.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountQueries extends Queries {

    public UserAccountQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(UserAccount userAccount) {

        int accountID = userAccount.getAccountID();
        int userID = userAccount.getUserID();
        int bankID = userAccount.getBankID();

        String query = insertIntoTableValues("user_account") + accountID + ", " + userID + ", " + bankID + ")";

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

}
