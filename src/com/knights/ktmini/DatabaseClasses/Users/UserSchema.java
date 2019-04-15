package com.knights.ktmini.DatabaseClasses.Users;

import models.User;
import models.queries.UserQueries;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserSchema {

	public static String username;
	public static String lastname;
	public static String middlename;
	public static String password;
	public static String contact;
	private Connection connection;

	public UserSchema(){

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@Spectre:1523:ORCL", "knights", "Oracle12345");
			System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
					+ connection.getMetaData().getDatabaseProductName());
		}
		catch(Exception e){e.printStackTrace();}

	}

	public boolean insertValue(){

		UserQueries userQueries = new UserQueries(connection);
		User user = new User(username, middlename, lastname, contact,password);

		if(userQueries.checkInsert(username,password)) {

			try {
				if(!user.getLastName().isEmpty()) {
					userQueries.insert_record(user);
					return true;
				}
				else{
					return false;
				}
			}
			catch(Exception ex){ex.printStackTrace();return false;}
		}
		else{
			new UserLogin(userQueries.getMainUserID(user));
			return false;
		}
	}

}
