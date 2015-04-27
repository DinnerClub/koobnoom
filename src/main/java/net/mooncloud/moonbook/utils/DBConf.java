package net.mooncloud.moonbook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConf
{
	static String driver_class = "com.mysql.jdbc.Driver";
	static String isee = "jdbc:mysql://127.16.1.37:3306/data_tb?autoReconnect=true&failOverReadOnly=false&maxReconnects=10&characterEncoding=utf8"; // 127.0.0.1
	// static String isee =
	// "jdbc:mysql://121.196.137.240:30002/data_tb?autoReconnect=true&failOverReadOnly=false&maxReconnects=10&characterEncoding=utf8";
	static String user_name = "root";
	static String passwd = "root";

	/**
	 * Returns a connection object o the DB
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver_class);

		if (user_name == null)
		{
			return DriverManager.getConnection(isee);
		}
		else
		{
			return DriverManager.getConnection(isee, user_name, passwd);
		}
	}

	/**
	 * Returns a connection object o the DB
	 * 
	 * @param driverclass
	 *            com.mysql.jdbc.Driver
	 * @param url
	 * @param user
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection(String driverclass, String url, String user, String password) throws ClassNotFoundException, SQLException
	{
		Class.forName(driverclass);

		if (user == null)
		{
			return DriverManager.getConnection(url);
		}
		else
		{
			return DriverManager.getConnection(url, user, password);
		}
	}
}
