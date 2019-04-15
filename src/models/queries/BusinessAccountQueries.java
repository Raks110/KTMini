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
}
