package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import extra.Constantes;
import modelo.Comentario;
import modelo.Publicacion;
import modelo.Usuario;

public class Comentarios implements Connectable<Comentario> {
	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();
	private static HashMap<String, String> queries = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("all", "select * from comentarios");
			put("insert", "insert into comentarios values(?,?,?,?,?,default)");
			put("count", "select count(*) as cantidad from comentarios");
			put("update",
					"update comentarios set descripcion=?, puntaje=?, fechaComentario=?, habilitado=? where idUsuario=? and idPublicacion=?");
			put("get", "select * from comentarios where idUsuario=? and idPublicacion=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<Comentario> m;

	@Override
	public ArrayList<Comentario> getAll() {
		cn = new Conexion();
		m = new ArrayList<Comentario>();
		//
		// String nombreApellidoUsuario = null;
		// String rutaFotoPerfilUsuario = null;

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Comentario o = new Comentario();
				o = readPs_Comentario(rs);
				// rutaFotoPerfilUsuario = this.getRutaFotoPerfil_Usuario(o.getIdUsuario());
				// nombreApellidoUsuario = this.getNombreApellido_Usuario(o.getIdUsuario());
				// if (rutaFotoPerfilUsuario.isEmpty())
				// rutaFotoPerfilUsuario = Constantes.RUTAuserNoPhoto;
				// o.setRutaFotoPerfilUsuario(rutaFotoPerfilUsuario);
				// o.setNombreApellidoUsuario(nombreApellidoUsuario);
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
	public Comentario get(Comentario obj) {
		cn = new Conexion();
		Comentario o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				o = new Comentario();
				o = readPs_Comentario(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Comentario obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {

			Usuarios usuarios = new Usuarios();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(obj.getIdUsuario());
			if (usuarios.get(usuario) == null)
				return false;

			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if (publicaciones.get(publicacion) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ps.setString(3, obj.getDescripcion());
			ps.setDate(4, obj.getFechaComentario());
			ps.setInt(5, obj.getPuntaje());
			ps.setBoolean(6, obj.isHabilitado());
			ps.executeUpdate();
			correcto = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean update(Comentario obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {

			Usuarios usuarios = new Usuarios();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(obj.getIdUsuario());
			if (usuarios.get(usuario) == null)
				return false;

			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if (publicaciones.get(publicacion) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getDescripcion());
			ps.setInt(2, obj.getPuntaje());
			ps.setDate(3, obj.getFechaComentario());
			ps.setBoolean(4, obj.isHabilitado());
			ps.setInt(5, obj.getIdUsuario());
			ps.setInt(6, obj.getIdPublicacion());
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
	public boolean remove(Comentario obj) {
		Comentario u = new Comentario();
		u.setIdUsuario(obj.getIdUsuario());
		u.setIdPublicacion(obj.getIdPublicacion());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* DAO - FUNCIONES READ/ WRITE ********************** ///

	private Comentario readPs_Comentario(ResultSet rs) throws SQLException {
		Comentario o = new Comentario();
		o.setIdUsuario(rs.getInt(cCampo.idUsuario));
		o.setIdPublicacion(rs.getInt(cCampo.idPublicacion));
		o.setDescripcion(rs.getString(cCampo.descripcion));
		o.setFechaComentario(rs.getDate(cCampo.fechaComentario));
		o.setPuntaje(rs.getInt(cCampo.puntaje));
		o.setHabilitado(rs.getBoolean(cCampo.habilitado));
		/// -----
		Usuarios usuariosDAO = new Usuarios();
		String nombreApellidoUsuario = null;
		String rutaFotoPerfilUsuario = null;
		rutaFotoPerfilUsuario = usuariosDAO.getRutaFotoPerfil_Usuario(o.getIdUsuario());
		nombreApellidoUsuario = usuariosDAO.getNombreApellido_Usuario(o.getIdUsuario());
		if (rutaFotoPerfilUsuario.isEmpty())
			rutaFotoPerfilUsuario = Constantes.RUTAuserNoPhoto;
		o.setRutaFotoPerfilUsuario(rutaFotoPerfilUsuario);
		o.setNombreApellidoUsuario(nombreApellidoUsuario);
		return o;
	}
	
	/// ********************* LAMBDA - Funciones de obtención de datos ******** ///
	public ArrayList<Comentario> getAllByIdPublicacion(int idPublicacion) {
		ArrayList<Comentario> listaComentarios = new ArrayList<Comentario>();
		getAll().forEach(item -> {
			if (item.getIdPublicacion() == idPublicacion)
				listaComentarios.add(item);
		});
		return listaComentarios;
	}
}
