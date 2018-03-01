package com.blog.comments.dao;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static final String URL = "jdbc:mysql://localhost:3306/perfectsense";
	public static final String USER = "root";
	public static final String PASS = "passw0rd123";

	/**
	 * Get a connection to database
	 * 
	 * @return Connection object
	 */
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	/**
	 * Close a connection to database
	 * 
	 * @param Connection
	 *            object
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				/* ignored */}
		}
	}

}