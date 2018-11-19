package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Partido;

public class Partidos implements Connectable<Partido> {
	
	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from partidos");
		put("insert", "insert into partidos values(null,?,default)");
		put("count", "select count(*) as cantidad from partidos");
		put("update","update partidos set nombre=?, habilitado=? where idPartido=?");
		put("get","select * from partidos where idTipoAlojamiento=?");
		put("like", "");
			
	}};
	
	private Conexion cn;
	private ArrayList<Partido> m;
	
	@Override
	public ArrayList<Partido> getAll() {
		cn = new Conexion();
		m = new ArrayList<Partido>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Partido o = new Partido();
				 o.setIdPartido(rs.getInt(1));
				 o.setNombre(rs.getString(2));
				 o.setHabilitado(rs.getBoolean(3));
				 m.add(o);
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return m;
	}

	@Override
	public ArrayList<Partido> getLike(String like) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		cn = new Conexion();
		int cantidad = 0;
		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("count"));
			
			if(rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return cantidad;
	}

	@Override
	public Partido get(Partido obj) {
		cn = new Conexion();
		Partido o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdPartido());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Partido();
				 o.setIdPartido(rs.getInt(1));
				 o.setNombre(rs.getString(2));
				 o.setHabilitado(rs.getBoolean(3));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Partido obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setString(1, obj.getNombre());
			ps.executeUpdate();
			correcto = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean update(Partido obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getNombre());
			ps.setBoolean(2, obj.isHabilitado());
			ps.setInt(3, obj.getIdPartido());
			if(ps.executeUpdate() != 0)
				correcto = true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean remove(Partido obj) {
		Partido u = new Partido();
		u.setIdPartido(obj.getIdPartido());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}
