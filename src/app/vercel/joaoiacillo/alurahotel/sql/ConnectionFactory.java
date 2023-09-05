package app.vercel.joaoiacillo.alurahotel.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Main factory for estabilishing connections with databases through JDBC.
 * @author João Iacillo
 */
public class ConnectionFactory {

	private final static String DB_NAME = "hotel_alura";

	private final static String DB_URL_QUERIES = "useTimezone=true&serverTimezone=UTC";
	private final static String DB_URL = String.format("jdbc:mysql://localhost/%s?%s", DB_NAME, DB_URL_QUERIES);
	
	private final static String DB_USER = "root";
	private final static String DB_PWD = "root";
	
	private static DataSource dataSource = null;
	
	public static void init() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(DB_URL);
		comboPooledDataSource.setUser(DB_USER);
		comboPooledDataSource.setPassword(DB_PWD);
		comboPooledDataSource.setMaxPoolSize(5);
		dataSource = comboPooledDataSource;
	}
	
	public static Connection getConnection() {
		if (dataSource == null) throw new RuntimeException("Initialize the ConnectionFactory before getting a connection.");
		
		try {
			return dataSource.getConnection();
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
}
