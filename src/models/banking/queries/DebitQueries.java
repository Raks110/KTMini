package models.banking.queries;

import models.banking.Debit;
import models.queries.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DebitQueries extends Queries {

    DebitQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(Debit debit) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

         int debitID = debit.getDebitID();
         int accountID = debit.getAccountID();

         double amountAvailable = debit.getAmountAvailable();

         String startDate = stringValue(format.format(debit.getStartDate()));

         String query = insertIntoTableValues("debit") + 9000 + ", " + 5000 + ", "
                 + amountAvailable + ", " + startDate + ", " + stringValue("active") + ")";

         System.out.println(query);

         try {

             query_statement.executeUpdate(query);
             System.out.println("Successfully inserted Debit record..");

             return true;

         } catch(SQLException exception) {
             exception.printStackTrace();


             return false;
         }

    }

    // Retrieve and fill object pending..
}
