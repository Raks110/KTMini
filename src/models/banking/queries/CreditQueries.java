package models.banking.queries;

import models.banking.Credit;
import models.queries.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CreditQueries extends Queries {

    public CreditQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(Credit credit) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        int creditID = credit.getCreditID();
        int accountID = credit.getAccountID();

        double creditLimit = credit.getCreditLimit(); // changed from amountAvailble to creditLimit to avoid confusion
        double amountRemanining = credit.getAmountRemanining();

        String startDate_string = stringValue(format.format(credit.getStartDate()));
        String dueDate_string  = stringValue(format.format(credit.getDueDate()));

        String status = stringValue("active");

        String query = insertIntoTableValues("credit") + creditID + ", " + accountID + ", " + creditLimit + ", " + amountRemanining
                + ", " + stringValue(startDate_string) + ", " + stringValue(dueDate_string) + ", " + stringValue("active") + ")";

        System.out.println(query);

        try {

            query_statement.executeUpdate(query);
            System.out.println("Credit record successfully added..");

            return true;

        } catch(SQLException exception) {
            exception.printStackTrace();
            System.out.println("Exception in Credit queries..");

            return false;
        }

    }
}
