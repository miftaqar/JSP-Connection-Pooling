package com.primetgi.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.primetgi.org.utilities.DBUtils;

public class LoginDAO {

	public static boolean validate(String username, String password) throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean status = false;

		try {
			
			if (connection == null || connection.isClosed()) {
				connection = DBUtils.getConnection();
				System.out.println("Connection Successfully Estabilished");
			}

			statement = connection
					.prepareStatement("select username, password  from tbl_Login where username = ? and password = ?");
			statement.setString(1, username);
			statement.setString(2, password);

			resultSet = statement.executeQuery();

			if (resultSet != null) {
				status = resultSet.next();
			} else {
				status = false;
			}

			System.out.println("Query Status: " + status);

		} finally {
			if (resultSet != null) {
				System.out.println("Closing the result set");
				resultSet.close();
			}

			if (statement != null) {
				System.out.println("Closing the prepared statement");
				statement.close();
			}

			if (connection != null) {
				System.out.println("Closing the connection");
				connection.close();
			}

		}

		return status;

	}
}
