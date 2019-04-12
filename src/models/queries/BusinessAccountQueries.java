package models.queries;

import models.BusinessAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class BusinessAccountQueries extends Queries {

    public BusinessAccountQueries(Connection connection) {
        super((connection));
    }

    public boolean insert_record(BusinessAccount businessAccount) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        int businessAccountID = businessAccount.getBusinessAccountID();
        int bankID = businessAccount.getBankID();
        int businessID = businessAccount.getBusinessID();

        double amount = businessAccount.getAmount();

        //Convert from Date datatype to MySQL compatible string format of date..

        String startDate_string = stringValue(format.format(businessAccount.getStartDate())); //gives quotes to date values for SQL
        String endDate_string = stringValue(format.format(businessAccount.getEndDate()));

        //String active = "active";
        // inserting "active".. When business account is deleted, mark it inactive..

        try {

            String query = insertIntoTableValues("businessAccount") + businessAccountID + ", " + businessID + ", " + bankID
                    + ", " + amount + ", " + startDate_string + ", " + endDate_string + ", " + stringValue("active") + ")";

            System.out.println(query);

            query_statement.executeUpdate(query);

        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return false;
    }

    public void display_relation() {

    }

    private void print_util(ResultSet resultSet) {

    }
}
