package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Publicacion;
import modelo.Telefono;

public class Telefonos implements Connectable<Telefono> {

	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from telefonos");
		put("insert", "insert into telefonos values(null,?,?,default)");
		put("count", "select count(*) as cantidad from telefonos");
		put("update","update telefonos set idPublicacion=?, numero=?, habilitado=? where idTelefono=?");
		put("get","select * from telefonos where idTelefono=?");
		put("like", "");
			
	}};

	private Conexion cn;
	private ArrayList<Telefono> m;
	
	@Override
	public ArrayList<Telefono> getAll() {
		cn = new Conexion();
		m = new ArrayList<Telefono>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Telefono o = new Telefono();
				 o.setIdTelefono(rs.getInt(1));
				 o.setIdPublicacion(rs.getInt(2));
				 o.setIdTelefono(rs.getInt(3));
				 o.setHabilitado(rs.getBoolean(4));
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
	public ArrayList<Telefono> getLike(String like) {
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
	public Telefono get(Telefono obj) {
		cn = new Conexion();
		Telefono o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdTelefono());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Telefono();
				o.setIdTelefono(rs.getInt(1));
				o.setIdPublicacion(rs.getInt(2));
				o.setIdTelefono(rs.getInt(3));
				o.setHabilitado(rs.getBoolean(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Telefono obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
						

			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if(publicaciones.get(publicacion) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdPublicacion());
			ps.setString(2, obj.getNumero());
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
	public boolean update(Telefono obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			
		
			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if(publicaciones.get(publicacion) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setInt(1, obj.getIdPublicacion());
			ps.setString(2, obj.getNumero());
			ps.setBoolean(3, obj.isHabilitado());
			ps.setInt(4, obj.getIdTelefono());
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
	public boolean remove(Telefono obj) {
		Telefono u = new Telefono();
		u.setIdTelefono(obj.getIdTelefono());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}