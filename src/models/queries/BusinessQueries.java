package models.queries;

import models.Business;

import java.sql.Connection;

public class BusinessQueries extends Queries {

    public BusinessQueries(Connection connection) {
        super(connection);

    }


    public boolean insert_record(Business business) {

        int businessID = business.getBusinessID();
        int managerID = business.getManagerID();

        String name = business.getName();

        String query = insertIntoTableValues("business") + businessID + ", " + managerID + ", " + stringValue(name) + ")";

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
