package com.usc.csci587;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtils {

	private static Connection conn;

	public static Connection getInstance(String url,String uname,String pass) throws Exception {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		if (conn == null) {
			conn = DriverManager.getConnection(url, uname, pass);
			conn.setAutoCommit(false);
		}
		return conn;

	}

}
