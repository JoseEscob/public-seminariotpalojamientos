package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Favorito;
import modelo.Publicacion;
import modelo.Usuario;

public class Favoritos implements Connectable<Favorito> {

	
	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from favoritos");
		put("insert", "insert into favoritos values(?,?,?,default)");
		put("count", "select count(*) as cantidad from favoritos");
		put("update","update favoritos set idFavorita=?, habilitado=? where idUsuario=? and idPublicacion=?");
		put("get","select * from favoritos where idUsuario=? and idPublicacion=?");
		put("like", "");
			
	}};
		
	private Conexion cn;
	private ArrayList<Favorito> m;
	
	@Override
	public ArrayList<Favorito> getAll() {
		cn = new Conexion();
		m = new ArrayList<Favorito>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Favorito o = new Favorito();
				 o.setIdFavorita(rs.getInt(1));
				 o.setIdUsuario(rs.getInt(2));
				 o.setIdPublicacion(rs.getInt(3));
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
	public ArrayList<Favorito> getLike(String like) {
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
	public Favorito get(Favorito obj) {
		cn = new Conexion();
		Favorito o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Favorito();
				o.setIdFavorita(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdPublicacion(rs.getInt(3));
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
	public boolean insert(Favorito obj) {
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
			ps.setInt(1, obj.getIdFavorita());
			ps.setInt(2, obj.getIdUsuario());
			ps.setInt(3, obj.getIdPublicacion());
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
	public boolean update(Favorito obj) {
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
			ps.setInt(1, obj.getIdFavorita());
			ps.setBoolean(2, obj.isHabilitado());
			ps.setInt(3, obj.getIdUsuario());
			ps.setInt(4, obj.getIdPublicacion());
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
	public boolean remove(Favorito obj) {
		Favorito u = new Favorito();
		u.setIdUsuario(obj.getIdUsuario());
		u.setIdPublicacion(obj.getIdPublicacion());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}
