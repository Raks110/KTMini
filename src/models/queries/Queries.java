package models.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Queries {

    public static Connection connection;
    public static Statement query_statement;

    public Queries(Connection connection) {

        init_connection_with_database(connection);

    }

    public Queries() {}

    private void init_connection_with_database(Connection connection) {

        this.connection = connection;

        try {
            query_statement = connection.createStatement();

        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public boolean insert_record(Object any) {
        // Override this moethod in ALL subclasses

        return true;
    }

    public void display_relation() {
        //Override this method in ALL subclasses..
    }



    public String insertIntoTableValues(String table) {
        return "INSERT INTO " + table + " VALUES(";
    }

    public String stringValue(String string) {
        return "\"" + string + "\"";
    }

    public static boolean isEmpty(String query){
		try {

			ResultSet result = query_statement.executeQuery(query);
			return !result.next();

		} catch (Exception exception) {
			System.out.println(exception);
			return true;
		}
	}


}
