package models.queries;

import models.Business;

import java.sql.Connection;
import java.sql.ResultSet;

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

    public int getBID(int managerID){
		try {
			String query = "SELECT BID FROM business WHERE managerID = " + managerID;
			System.out.println(query);

			ResultSet resultSet = query_statement.executeQuery(query);
			resultSet.next();
			return resultSet.getInt("BID");

		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public boolean checkInsert(int managerID,String password) {

		return isEmpty("SELECT * FROM business WHERE managerID = '" + managerID + "' AND password= '" + password + "'");

	}

}
