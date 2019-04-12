package models.queries;

import java.sql.Connection;

public class GlobalLedgerQueries extends Queries {

    public GlobalLedgerQueries(Connection connection) {
        super(connection);
    }

    // USE TRIGGERS TO UPDATE THIS IDK HOW PL/SQL TRIGGER FUNCTIONS REQUIRE THIS CLASS. 
}
