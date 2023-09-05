package app.vercel.joaoiacillo.alurahotel.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.vercel.joaoiacillo.alurahotel.dao.AuthDAO;
import app.vercel.joaoiacillo.alurahotel.sql.ConnectionFactory;

/**
 * Handle the authentication logic. All methods are static for global
 * application usage.
 * 
 * @author João Iacillo <joaopiacillo@outlook.com.br>
 */
public class Authenticator {
	
	private static String authenticatedUser;
	private static boolean authenticated = false;
	
	public static boolean isAuthenticated() {
		return authenticated;
	}
	
	/**
	 * Return the current authenticated user or null value if there was no
	 * authentication made previously.
	 */
	public static String getAuthenticatedUser() {
		return authenticatedUser;
	}
	
	/**
	 * Logs out from the system. Makes authenticated-only operations stop working.
	 */
	public static void logOut() {
		Authenticator.authenticatedUser = null;
		Authenticator.authenticated = false;
	}
	
	/**
	 * Try to authenticate using the provided name and password as credentials.
	 * If the credentials aren't correct, then it returns {@code false}.
	 * @return Whether the user is now authenticated or not.
	 */
	public static AuthenticationResult authenticate(String name, String password) {
		AuthenticationResult result = Authenticator.checkCredentials(name, password);
		
		if (result == AuthenticationResult.CONNECTED) {
			Authenticator.authenticatedUser = name;
			Authenticator.authenticated = true;
		}
		
		return result;
	}
	
	/**
	 * Check the credentials on the database. If the user exists and the
	 * password is correct, then it returns true. Otherwise, false.
	 * @return Whether the credentials are valid or not.
	 */
	private static AuthenticationResult checkCredentials(String name, String password) {
		
		System.out.println("Trying to connect the user " + name + "...");
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			AuthDAO dao = new AuthDAO(conn);
			AuthenticationResult result = dao.checkCredentials(name, password);
			return result;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
	}
	
}
