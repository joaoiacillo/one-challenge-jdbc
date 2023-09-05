package app.vercel.joaoiacillo.alurahotel.controller;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import app.vercel.joaoiacillo.alurahotel.dao.GuestDAO;
import app.vercel.joaoiacillo.alurahotel.model.GuestModel;
import app.vercel.joaoiacillo.alurahotel.sql.ConnectionFactory;

public class GuestController implements IController<GuestModel> {
	
	public void insert(GuestModel guest) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			dao.insert(guest);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
	}
	
	public GuestModel get(int id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			return dao.select(id);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean remove(int id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			return dao.remove(id);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public boolean update(int id, GuestModel model) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			return dao.update(id, model);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
	public ArrayList<GuestModel> list() {
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			return dao.list();
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
	}
	
}
