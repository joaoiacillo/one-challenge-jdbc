package app.vercel.joaoiacillo.alurahotel.dao;

import java.util.ArrayList;

/**
 * Interface for accessing data from a specific entity.
 * 
 * @param <T> The entity model
 */
public interface IDataAccessObject<T> {
	
	void insert(T model);
	boolean remove(int id);
	boolean update(int id, T updatedModel);
	
	ArrayList<T> list();
	T select(int id);
	
}
