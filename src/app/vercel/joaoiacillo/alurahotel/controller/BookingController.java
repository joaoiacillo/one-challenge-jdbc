package app.vercel.joaoiacillo.alurahotel.controller;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import app.vercel.joaoiacillo.alurahotel.dao.BookingDAO;
import app.vercel.joaoiacillo.alurahotel.dao.GuestDAO;
import app.vercel.joaoiacillo.alurahotel.model.BookingModel;
import app.vercel.joaoiacillo.alurahotel.model.GuestModel;
import app.vercel.joaoiacillo.alurahotel.sql.ConnectionFactory;

public class BookingController implements IController<BookingModel> {
	
	public void insert(BookingModel booking) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			dao.insert(booking);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
	}
	
	public BookingModel get(int id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			return dao.select(id);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean remove(int id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			return dao.remove(id);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean update(int id, BookingModel model) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			return dao.update(id, model);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public ArrayList<BookingModel> list() {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			return dao.list();
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
}
