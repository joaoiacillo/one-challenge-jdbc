package app.vercel.joaoiacillo.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.vercel.joaoiacillo.alurahotel.auth.AuthenticationResult;

public class AuthDAO {

	private Connection connection;

	public AuthDAO(Connection connection) {
		this.connection = connection;
	}
	
	public AuthenticationResult checkCredentials(String username, String password) {
		String sql = "SELECT * FROM tbAdministrator WHERE USERNAME = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setString(1, username);
			stmt.execute();
			
			try (ResultSet rset = stmt.getResultSet()) {
				
				while (rset.next()) {
					if (password.equals(rset.getString("PASSWORD"))) {
						System.out.println("Login: User connected.");
						return AuthenticationResult.CONNECTED;
					}
					
					System.err.println("Login error: Wrong password.");
					return AuthenticationResult.WRONG_PASSWORD;
				}
				
				System.err.println("Login error: Wrong username.");
				return AuthenticationResult.WRONG_USERNAME;
				
			}
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	
}
