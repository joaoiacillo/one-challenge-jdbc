package app.vercel.joaoiacillo.alurahotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.vercel.joaoiacillo.alurahotel.auth.AuthenticationResult;
import app.vercel.joaoiacillo.alurahotel.model.GuestModel;

public class GuestDAO implements IDataAccessObject<GuestModel> {
	
	private Connection connection;

	public GuestDAO(Connection conn) {
		this.connection = conn;
	}
	
	public void insert(GuestModel guest) {
		String sql = "INSERT INTO tbGuest (FIRST_NAME, LAST_NAME, BIRTHDAY, NATIONALITY, PHONE) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, guest.getFirstName());
			stmt.setString(2, guest.getLastName());
			stmt.setDate(3, guest.getBirthday());
			stmt.setString(4, guest.getNationality());
			stmt.setString(5, guest.getPhoneNumber());
			stmt.execute();
			
			try (ResultSet rset = stmt.getGeneratedKeys()) {
				
				while (rset.next()) {
					guest.setId(rset.getInt(1));
				}
				
			}
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public GuestModel select(int id) {
		String sql = "SELECT FIRST_NAME, LAST_NAME, BIRTHDAY, NATIONALITY, PHONE FROM tbGuest WHERE GUEST_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.execute();
			
			try (ResultSet rset = stmt.getResultSet()) {
				
				while (rset.next()) {
					return new GuestModel(
						id, 
						rset.getString("FIRST_NAME"), 
						rset.getString("LAST_NAME"), 
						rset.getString("PHONE"), 
						rset.getString("NATIONALITY"), 
						rset.getDate("BIRTHDAY")
					);
				}
				
			}
			
			return null;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	private void removeBookings(int id) {
		String sql = "DELETE FROM tbBooking WHERE GUEST_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.execute();
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean remove(int id) {
		this.removeBookings(id);
		
		String sql = "DELETE FROM tbGuest WHERE GUEST_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.execute();
			
			int updateCount = stmt.getUpdateCount();
			return updateCount > 0;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean update(int id, GuestModel newModel) {
		String sql = "UPDATE tbGuest SET FIRST_NAME = ?, LAST_NAME = ?, PHONE = ?, NATIONALITY = ?, BIRTHDAY = ? WHERE GUEST_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setString(1, newModel.getFirstName());
			stmt.setString(2, newModel.getLastName());
			stmt.setString(3, newModel.getPhoneNumber());
			stmt.setString(4, newModel.getNationality());
			stmt.setDate(5, newModel.getBirthday());
			stmt.setInt(6, id);
			stmt.execute();
			
			int updateCount = stmt.getUpdateCount();
			return updateCount > 0;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public ArrayList<GuestModel> list() {
		ArrayList<GuestModel> names = new ArrayList<>();
		String sql = "SELECT GUEST_ID, FIRST_NAME, LAST_NAME, PHONE, NATIONALITY, BIRTHDAY FROM tbGuest";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			stmt.execute();
			
			try (ResultSet rset = stmt.getResultSet()) {
				
				while (rset.next()) {
					GuestModel model = new GuestModel(
						rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5),
						rset.getDate(6)
					);
					names.add(model);
				}
				
			}
			
			return names;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
}
