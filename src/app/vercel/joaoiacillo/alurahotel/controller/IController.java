package app.vercel.joaoiacillo.alurahotel.controller;

import java.util.ArrayList;

/**
 * Interface for controlling a specific entity.
 * 
 * @param <T> The entity model
 */
public interface IController<T> {

	void insert(T model);
	boolean remove(int id);
	boolean update(int id, T updatedModel);
	
	ArrayList<T> list();
	T get(int id);
	
}
