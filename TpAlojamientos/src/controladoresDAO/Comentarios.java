package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Comentario;
import modelo.Publicacion;
import modelo.Usuario;

public class Comentarios implements Connectable<Comentario> {
	

	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from comentarios");
		put("insert", "insert into comentarios values(?,?,?,?,default)");
		put("count", "select count(*) as cantidad from comentarios");
		put("update","update comentarios set descripcion=?, puntaje=?, habilitado=? where idUsuario=? and idPublicacion=?");
		put("get","select * from comentarios where idUsuario=? and idPublicacion=?");
		put("like", "");
			
	}};

	private Conexion cn;
	private ArrayList<Comentario> m;
	
	@Override
	public ArrayList<Comentario> getAll() {
		cn = new Conexion();
		m = new ArrayList<Comentario>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Comentario o = new Comentario();
				 o.setIdUsuario(rs.getInt(1));
				 o.setIdPublicacion(rs.getInt(2));
				 o.setDescripcion(rs.getString(3));
				 o.setPuntaje(rs.getInt(4));
				 o.setHabilitado(rs.getBoolean(5));
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
	public ArrayList<Comentario> getLike(String like) {
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
	public Comentario get(Comentario obj) {
		cn = new Conexion();
		Comentario o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());		
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Comentario();
				o.setIdUsuario(rs.getInt(1));
				o.setIdPublicacion(rs.getInt(2));
				o.setDescripcion(rs.getString(3));
				o.setPuntaje(rs.getInt(4));
				o.setHabilitado(rs.getBoolean(5));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Comentario obj) {
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
			ps.setString(3, obj.getDescripcion());
			ps.setInt(4, obj.getPuntaje());
			ps.setBoolean(5, obj.isHabilitado());		
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
	public boolean update(Comentario obj) {
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
			ps.setString(1, obj.getDescripcion());
			ps.setInt(2, obj.getPuntaje());
			ps.setBoolean(3, obj.isHabilitado());
			ps.setInt(4, obj.getIdUsuario());
			ps.setInt(5, obj.getIdPublicacion());
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
	public boolean remove(Comentario obj) {
		Comentario u = new Comentario();
		u.setIdUsuario(obj.getIdUsuario());
		u.setIdPublicacion(obj.getIdPublicacion());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}


}