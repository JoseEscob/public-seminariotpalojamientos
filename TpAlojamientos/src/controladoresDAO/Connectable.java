package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface Connectable<T> {
	
	//los veremos despues 
	//ublic ArrayList<T> getSegment(int start, int total);
	//public ArrayList<T> getLikeSegment(int start, int total, String like);

	public ArrayList<T> getAll();
	public ArrayList<T> getLike(String like);
	public int getCount();
	public T get(T obj);
	public boolean insert(T obj);
	public boolean update(T obj);
	public boolean remove(T obj);
	
//	T readPs_Clase(ResultSet rs);
//	PreparedStatement writePs_Clase(T obj, PreparedStatement ps);
}
