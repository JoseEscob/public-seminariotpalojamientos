package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

import extra.Conexion;
import modelo.Usuario;

public class Usuarios implements Connectable<Usuario>{
	

	/*private static final String qSelectAll="select * from alumnos";
	private static final String qSelectLimit="select * from alumnos limit ?,?";
	private static final String qSelectLike="select * from alumnos where nombre like ? or apellido like ? or dni like ? or legajo like ?";
	private static final String qSelectLikeLimit="select * from alumnos where lower(nombre) like ? or lower(apellido) like ? or dni like ? or legajo like ? limit ?,?";
	private static final String qSelectOne="select * from alumnos where ("+id+"=?)";
	private static final String qUpdate="update alumnos set "+nombre+"=?,"+apellido+"=?,"+dni+"=?,"+legajo+"=?,"+habilitado+"=? where "+id+"=?";
	private static final String qInsert="insert into alumnos values(null,?,?,?,?,default)";
	private static final String qSelectCount="select count(alumnos.id) as cantidad from alumnos";*/
	private static HashMap<String,String> queries = new HashMap<String, String>(){{
			put("all", "select * from usuarios");
			put("insert", "insert into usuarios values(?,?,?,?,?,?,?,?,?,?)");
			
	}};
	
	
	
	private Conexion cn;
	private ArrayList<Usuario> usuarios;


	@Override
	public ArrayList<Usuario> getAll() {
		cn = new Conexion();
		usuarios = new ArrayList<Usuario>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Usuario usuario = new Usuario();
				 usuario.setIdUsuario(rs.getInt(1));
				 usuario.setNombre(rs.getString(2));
				 usuario.setApellido(rs.getString(3));
				 usuario.setDni(rs.getString(4));
				 usuario.setMail(rs.getString(5));
				 usuario.setFechaNac(rs.getDate(6));
				 usuario.setNroUsuario(rs.getString(7));
				 usuario.setClaveUsuario(rs.getString(8));
				 usuario.setPuntaje(rs.getFloat(9));
				 usuario.setHabilitado(rs.getBoolean(10));
				 usuarios.add(usuario);
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
		 return usuarios;
	}

	@Override
	public ArrayList<Usuario> getLike(String like) {
		/*like = "%"+like+"%";
		cn = new Conexion();
		usuarios = new ArrayList<Usuario>();
		try {
			PreparedStatement ps = cn.Open().prepareStatement(qSelectLike);
			ps.setString(1, like);
			ps.setString(2, like);
			ps.setString(3, like);
			ps.setString(4, like);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario(rs.getLong(id),rs.getString(nombre),rs.getString(apellido),rs.getLong(dni),rs.getInt(legajo),rs.getBoolean(habilitado));
				usuarios.add(usuario);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return usuarios;*/
		return null;
	}

	@Override
	public int getCount() {
		cn = new Conexion();
		int cantidad = 0;
		try {
			cn.Open();
			ResultSet rs = cn.query("");
			
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
	public Usuario get(Usuario obj) {
		/*cn = new Conexion();
		Usuario usuario;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(qSelectOne);
			ps.setLong(1, obj.getId());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario(rs.getLong(id),rs.getString(nombre),rs.getString(apellido),rs.getLong(dni),rs.getInt(legajo),rs.getBoolean(habilitado));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return usuario;*/
		return null;
	}

	@Override
	public boolean insert(Usuario obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1,obj.getIdUsuario());
			ps.setString(2, obj.getNombre());
			ps.setString(3, obj.getApellido());
			ps.setString(4, obj.getDni());
			ps.setString(5, obj.getMail());
			ps.setDate(6, obj.getFechaNac());
			ps.setString(7, obj.getNroUsuario());
			ps.setString(8, obj.getClaveUsuario());
			ps.setFloat(9, obj.getPuntaje());
			ps.setBoolean(10, obj.isHabilitado());
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
	public boolean update(Usuario obj) {
		/*if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			
			PreparedStatement ps = cn.Open().prepareStatement(qUpdate);
			ps.setString(1, obj.getNombre());
			ps.setString(2, obj.getApellido());
			ps.setLong(3, obj.getDNI());
			ps.setInt(4, obj.getLegajo());
			ps.setBoolean(5, obj.isHabilitado());
			ps.setLong(6, obj.getId());
			ps.executeUpdate();
			correcto = true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return correcto;*/
		return false;
	}

	@Override
	public boolean remove(Usuario obj) {
		/*Usuario u = this.get(new Usuario(obj.getId()));
		//u.setHabilitado(false);
		return this.update(u);*/
		return false;
	}
	
}
