package app.vercel.joaoiacillo.alurahotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.vercel.joaoiacillo.alurahotel.auth.AuthenticationResult;
import app.vercel.joaoiacillo.alurahotel.model.BookingModel;
import app.vercel.joaoiacillo.alurahotel.model.GuestModel;

public class BookingDAO implements IDataAccessObject<BookingModel> {
	
	private Connection connection;

	public BookingDAO(Connection conn) {
		this.connection = conn;
	}
	
	public void insert(BookingModel booking) {
		String sql = "INSERT INTO tbBooking (BOOKING_NUMBER, CHECKIN_DATE, CHECKOUT_DATE, PRICE, PAYMENT_METHOD, GUEST_ID) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			System.out.println(booking.getPaymentMethod());
			
			stmt.setString(1, booking.getNumber());
			stmt.setDate(2, booking.getCheckinDate());
			stmt.setDate(3, booking.getCheckoutDate());
			stmt.setDouble(4, booking.getPrice());
			stmt.setString(5, booking.getPaymentMethod());
			stmt.setInt(6, booking.getGuestId());
			stmt.execute();
			
			try (ResultSet rset = stmt.getGeneratedKeys()) {
				
				while (rset.next()) {
					booking.setId(rset.getInt(1));
				}
				
			}
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public BookingModel select(int id) {
		String sql = "SELECT BOOKING_NUMBER, CHECKIN_DATE, CHECKOUT_DATE, PRICE, PAYMENT_METHOD, GUEST_ID FROM tbBooking WHERE BOOKING_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			System.out.println("Using ID: " + id);
			stmt.setInt(1, id);
			stmt.execute();
			
			try (ResultSet rset = stmt.getResultSet()) {
				
				System.out.println("Opened result set");
				
				while (rset.next()) {
					return new BookingModel(
						id, 
						rset.getString(1), 
						rset.getDate(2), 
						rset.getDate(3), 
						rset.getDouble(4), 
						rset.getString(5),
						rset.getInt(6)
					);
				}
				
			}
			
			return null;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean remove(int id) {
		String sql = "DELETE FROM tbBooking WHERE BOOKING_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.execute();
			
			int updateCount = stmt.getUpdateCount();
			return updateCount > 0;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean update(int id, BookingModel newModel) {
		String sql = "UPDATE tbBooking SET CHECKIN_DATE = ?, CHECKOUT_DATE = ?, PRICE = ?, PAYMENT_METHOD = ?, GUEST_ID = ? WHERE BOOKING_ID = ?";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			
			stmt.setDate(1, newModel.getCheckinDate());
			stmt.setDate(2, newModel.getCheckoutDate());
			stmt.setDouble(3, newModel.getPrice());
			stmt.setString(4, newModel.getPaymentMethod());
			stmt.setInt(5, newModel.getGuestId());
			stmt.setInt(6, id);
			stmt.execute();
			
			int updateCount = stmt.getUpdateCount();
			return updateCount > 0;
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public ArrayList<BookingModel> list() {
		ArrayList<BookingModel> names = new ArrayList<>();
		String sql = "SELECT BOOKING_ID, BOOKING_NUMBER, CHECKIN_DATE, CHECKOUT_DATE, PRICE, PAYMENT_METHOD, GUEST_ID FROM tbBooking	";
		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			stmt.execute();
			
			try (ResultSet rset = stmt.getResultSet()) {
				
				while (rset.next()) {
					BookingModel model = new BookingModel(
							rset.getInt(1), 
							rset.getString(2), 
							rset.getDate(3), 
							rset.getDate(4), 
							rset.getDouble(5), 
							rset.getString(6),
							rset.getInt(7)
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
