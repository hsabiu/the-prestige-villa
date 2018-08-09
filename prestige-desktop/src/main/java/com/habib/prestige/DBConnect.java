package com.habib.prestige;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class DBConnect {
	public Connection con;
	public Statement st;

	DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			@SuppressWarnings("unused")
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prestige_villa_db", "root", "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}