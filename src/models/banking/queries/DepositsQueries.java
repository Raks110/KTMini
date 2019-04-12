package models.banking.queries;

import models.banking.Deposits;
import models.queries.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DepositsQueries extends Queries {

    public DepositsQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(Deposits deposits) {

        SimpleDateFormat format = new SimpleDateFormat("yyy-mm-dd");

        int depositID = deposits.getDepositID();
        int accountID = deposits.getAccountID();

        float interest = deposits.getInterest();

        String status = stringValue("active");

        String startDate_string = stringValue(format.format(deposits.getStartDate()));
        String endDate_string = stringValue(format.format(deposits.getEndDate()));

        String query = insertIntoTableValues("deposits") + 3500000 + ", " + 5000 + ", " + 7.5 + ", " + startDate_string + ", " + endDate_string + ", " + stringValue("active") + ")";

        System.out.println(query);

        try {

            query_statement.executeUpdate(query);

            System.out.println("Successfully inserted deposit record..");

            return true;

        } catch(SQLException exception) {
            exception.printStackTrace();

            return false;
        }

    }

}
