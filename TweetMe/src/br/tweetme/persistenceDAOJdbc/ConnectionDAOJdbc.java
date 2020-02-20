package br.tweetme.persistenceDAOJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDAOJdbc {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDAOJdbc.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
	
	public static Connection getConnection(boolean autoCommit) throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/tweetme";
		String user = "root";
		String password = "root";
		
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(autoCommit);
		
		return connection;
	}
}
