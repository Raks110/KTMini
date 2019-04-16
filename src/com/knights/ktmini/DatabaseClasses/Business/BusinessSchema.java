package com.knights.ktmini.DatabaseClasses.Business;

import models.queries.BusinessQueries;

import java.sql.Connection;
import java.sql.DriverManager;

public class BusinessSchema {

	public static Connection connection;

	public BusinessSchema(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@Spectre:1523:ORCL", "knights", "Oracle12345");
			System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
					+ connection.getMetaData().getDatabaseProductName());
		}
		catch(Exception e){e.printStackTrace();}

	}

	public boolean insertValue(String name, int mUIN,String password){

		BusinessQueries businessQueries = new BusinessQueries(connection);

		if(businessQueries.checkInsert(mUIN,password)) {

			try {
				if(!name.isEmpty()) {
					businessQueries.insert_record(mUIN,name,password);
					new BusinessLogin(mUIN,name);
					return true;
				}
				else{
					return false;
				}
			}
			catch(Exception ex){ex.printStackTrace();return false;}
		}
		else{
			new BusinessLogin(mUIN,name);
			return false;
		}
	}

}
