package models.queries;

import models.Bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankQueries extends Queries {

    public BankQueries(Connection connection) {
        super(connection);
    }

    public boolean insert_record(Bank bank) {

        int bankID = bank.getBankID();
        String name = bank.getName();

        String query = insertIntoTableValues("bank") + bankID + ", " + stringValue(name) + ")";

        System.out.println(query);

        try {

            query_statement.executeUpdate(query);

            return true;


        } catch(SQLException exception) {

            exception.printStackTrace();
            System.out.println("Exception in BankQueries..");

            return false;
        }

    }

    public void display_relation() {

    }

    private void print_util(ResultSet resultSet) {

    }


}
