package com.primetgi.org.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {

	static Connection connection = null;

	public static Connection getConnection() {
		try {

			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/UserDB");
			connection = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

}
