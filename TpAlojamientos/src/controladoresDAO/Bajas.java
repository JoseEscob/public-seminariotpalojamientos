package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Baja;
import modelo.Favorito;
import modelo.Publicacion;
import modelo.Usuario;

public class Bajas implements Connectable<Baja> {

	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from bajas");
		put("insert", "insert into bajas values(null,?,?,default,null,default,?,default)");
		put("count", "select count(*) as cantidad from bajas");
		put("update","update bajas set idUsuario=?, idPublicacion=?, fechaBaja=?, fechaSolucion=?, solucionado=?, razonBaja=?, habilitado=? where idBaja=?");
		put("get","select * from bajas where idBaja=?");
		put("like", "");
			
	}};

	private Conexion cn;
	private ArrayList<Baja> m;
	
	@Override
	public ArrayList<Baja> getAll() {
		cn = new Conexion();
		m = new ArrayList<Baja>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Baja o = new Baja();
				 o.setIdBaja(rs.getInt(1));
				 o.setIdUsuario(rs.getInt(2));
				 o.setIdPublicacion(rs.getInt(3));
				 o.setFechaBaja(rs.getDate(4));
				 o.setFechaSolucion(rs.getDate(5));
				 o.setSolucionado(rs.getBoolean(6));
				 o.setRazon_baja(rs.getString(7));
				 o.setHabilitado(rs.getBoolean(8));
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
	public ArrayList<Baja> getLike(String like) {
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
	public Baja get(Baja obj) {
		cn = new Conexion();
		Baja o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Baja();
				o.setIdBaja(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdPublicacion(rs.getInt(3));
				o.setFechaBaja(rs.getDate(4));
				o.setFechaSolucion(rs.getDate(5));
				o.setSolucionado(rs.getBoolean(6));
				o.setRazon_baja(rs.getString(7));
				o.setHabilitado(rs.getBoolean(8));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Baja obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
		
			Usuarios usuarios = new Usuarios();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(obj.getIdUsuario());
			if(usuarios.get(usuario) == null)
				return false;
			
			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if(publicaciones.get(publicacion) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ps.setString(3, obj.getRazon_baja());
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
	public boolean update(Baja obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			

			Usuarios usuarios = new Usuarios();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(obj.getIdUsuario());
			if(usuarios.get(usuario) == null)
				return false;
			
			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if(publicaciones.get(publicacion) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ps.setDate(3, obj.getFechaBaja());
			ps.setDate(4, obj.getFechaSolucion());
			ps.setBoolean(5, obj.isSolucionado());
			ps.setString(6, obj.getRazon_baja());
			ps.setBoolean(7, obj.isHabilitado());
			ps.setInt(8, obj.getIdBaja());
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
	public boolean remove(Baja obj) {
		Baja u = new Baja();
		u.setIdBaja(obj.getIdBaja());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}
