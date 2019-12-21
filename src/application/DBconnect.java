package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
	DBconnect()
	{}

public Connection getConn()
	{
		String dbUrl = "jdbc:mysql://localhost/gui";
		String dbuser ="root";
		String dbpass ="";
		Connection MyConn=null;
		try {
			MyConn = DriverManager.getConnection(dbUrl, dbuser, dbpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MyConn;
	
	}
	}

