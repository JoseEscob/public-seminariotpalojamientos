package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import extra.LOG;
import modelo.Usuario;

public class Usuarios implements Connectable<Usuario> {
//comentario!
	private static HashMap<String, String> queries = new HashMap<String, String>() {
		{
			put("all", "select * from usuarios");
			put("insert", "insert into usuarios values(null,?,?,?,?,?,?,?,?,default)");
			put("count", "select count(*) as cantidad from usuarios");
			put("update",
					"update usuarios set nombre=?, apellido=?, dni=?, mail=?, fechaNac=?, nroUsuario=?, clave=?, puntaje=?, habilitado=? where idUsuario=?");
			put("get", "select * from usuarios where idUsuario=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<Usuario> m;

	@Override
	public ArrayList<Usuario> getAll() {
		cn = new Conexion();
		m = new ArrayList<Usuario>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Usuario o = new Usuario();
				o.setIdUsuario(rs.getInt(1));
				o.setNombre(rs.getString(2));
				o.setApellido(rs.getString(3));
				o.setDni(rs.getString(4));
				o.setMail(rs.getString(5));
				o.setFechaNac(rs.getDate(6));
				o.setNroUsuario(rs.getString(7));
				o.setClaveUsuario(rs.getString(8));
				o.setPuntaje(rs.getFloat(9));
				o.setHabilitado(rs.getBoolean(10));
				m.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return m;
	}

	@Override
	public ArrayList<Usuario> getLike(String like) {
		/*
		 * 
		 * PROXIMAMENTEEEEEE
		 * 
		 * like = "%"+like+"%"; cn = new Conexion(); usuarios = new
		 * ArrayList<Usuario>(); try { PreparedStatement ps =
		 * cn.Open().prepareStatement(qSelectLike); ps.setString(1, like);
		 * ps.setString(2, like); ps.setString(3, like); ps.setString(4, like);
		 * ResultSet rs = ps.executeQuery();
		 * 
		 * while(rs.next()) { Usuario usuario = new
		 * Usuario(rs.getLong(id),rs.getString(nombre),rs.getString(apellido),rs.getLong
		 * (dni),rs.getInt(legajo),rs.getBoolean(habilitado)); usuarios.add(usuario); }
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }finally{ cn.close(); } return
		 * usuarios;
		 */
		return null;
	}

	@Override
	public int getCount() {
		cn = new Conexion();
		int cantidad = 0;
		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("count"));

			if (rs.next()) {
				cantidad = rs.getInt("cantidad");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cantidad;
	}

	@Override
	public Usuario get(Usuario obj) {
		cn = new Conexion();
		Usuario o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdUsuario());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Usuario();
				o.setIdUsuario(rs.getInt(1));
				o.setNombre(rs.getString(2));
				o.setApellido(rs.getString(3));
				o.setDni(rs.getString(4));
				o.setMail(rs.getString(5));
				o.setFechaNac(rs.getDate(6));
				o.setNroUsuario(rs.getString(7));
				o.setClaveUsuario(rs.getString(8));
				o.setPuntaje(rs.getFloat(9));
				o.setHabilitado(rs.getBoolean(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Usuario obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setString(1, obj.getNombre());
			ps.setString(2, obj.getApellido());
			ps.setString(3, obj.getDni());
			ps.setString(4, obj.getMail());
			ps.setDate(5, obj.getFechaNac());
			ps.setString(6, obj.getNroUsuario());
			ps.setString(7, obj.getClaveUsuario());
			ps.setFloat(8, obj.getPuntaje());

			ps.executeUpdate();
			correcto = true;
			LOG.info("Alta Exitosa - Usuario : " + obj.getNombre());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean update(Usuario obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getNombre());
			ps.setString(2, obj.getApellido());
			ps.setString(3, obj.getDni());
			ps.setString(4, obj.getMail());
			ps.setDate(5, obj.getFechaNac());
			ps.setString(6, obj.getNroUsuario());
			ps.setString(7, obj.getClaveUsuario());
			ps.setFloat(8, obj.getPuntaje());
			ps.setBoolean(9, obj.isHabilitado());
			ps.setInt(10, obj.getIdUsuario());
			if (ps.executeUpdate() != 0)
				correcto = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean remove(Usuario obj) {
		Usuario u = new Usuario();
		u.setIdUsuario(obj.getIdUsuario());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}
