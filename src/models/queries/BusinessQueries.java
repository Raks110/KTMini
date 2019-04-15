package models.queries;

import models.Business;

import java.sql.Connection;

public class BusinessQueries extends Queries {

    public BusinessQueries(Connection connection) {
        super(connection);

    }


    public boolean insert_record(int managerID,String name,String password) {

        String query = "INSERT INTO business (managerID,name,password) VALUES (" + managerID + ", '" + name + "', '" + password + "')";
        System.out.println(query);

        try {

            query_statement.executeUpdate(query);
            System.out.println("BusinessQuery sucessfully executed..");

            return true;

        } catch(Exception exception) {

            System.out.println("Exception in inserting BusinessQueries record..");
            exception.printStackTrace();

            return false;
        }

    }


}
