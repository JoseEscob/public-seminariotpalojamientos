package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ServidorException;
import extra.Conexion;
import extra.LOG;
import modelo.Favorito;
import modelo.Usuario;

public class Favoritos implements Connectable<Favorito> {

	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();
	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = -6388528118191925167L;

		{
			put("all", "select * from favoritos");
			put("insert", "insert into favoritos set idFavorita=?, habilitado=?, idUsuario=?, idPublicacion=?");
			put("count", "select count(*) as cantidad from favoritos");
			put("update", "update favoritos set idFavorita=?, habilitado=? where idUsuario=? and idPublicacion=?");
			put("get", "select * from favoritos where idUsuario=? and idPublicacion=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<Favorito> m;

	@Override
	public ArrayList<Favorito> getAll() {
		cn = new Conexion();
		m = new ArrayList<Favorito>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Favorito o = new Favorito();
				o = readPs_Favorito(rs);
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
	public Favorito get(Favorito obj) {
		cn = new Conexion();
		Favorito o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Favorito();
				o = readPs_Favorito(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	public Favorito get(int idUsuario, int idPublicacion) {
		cn = new Conexion();
		Favorito o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, idUsuario);
			ps.setInt(2, idPublicacion);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Favorito();
				o = readPs_Favorito(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Favorito obj) {
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

			Publicaciones publicacionesDAO = new Publicaciones();
			if (publicacionesDAO.getObjectByID(obj.getIdPublicacion()) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps = writePs_Favorito(obj, ps);
			LOG.info("INSERT Favoritos: " + ps.toString());
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
	public boolean update(Favorito obj) {
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

			Publicaciones publicacionesDAO = new Publicaciones();
			if (publicacionesDAO.getObjectByID(obj.getIdPublicacion()) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps = writePs_Favorito(obj, ps);
			LOG.info("UPDATE Favoritos: " + ps.toString());
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
	public boolean remove(Favorito obj) {
		Favorito u = new Favorito();
		u.setIdUsuario(obj.getIdUsuario());
		u.setIdPublicacion(obj.getIdPublicacion());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* DAO - FUNCIONES READ/ WRITE ********************** ///

	private Favorito readPs_Favorito(ResultSet rs) throws SQLException {
		Favorito o = new Favorito();
		o.setIdFavorita(rs.getInt(cCampo.idFavorita));
		o.setIdUsuario(rs.getInt(cCampo.idUsuario));
		o.setIdPublicacion(rs.getInt(cCampo.idPublicacion));
		o.setHabilitado(rs.getBoolean(cCampo.habilitado));
		return o;
	}

	private PreparedStatement writePs_Favorito(Favorito obj, PreparedStatement ps) throws SQLException {
		ps.setInt(1, obj.getIdFavorita());
		ps.setBoolean(2, obj.isHabilitado());
		ps.setInt(3, obj.getIdUsuario());
		ps.setInt(4, obj.getIdPublicacion());
		return ps;
	}

	/// ********************* LAMBDA - Funciones de obtención de datos ******** ///
	public ArrayList<Favorito> getAllByIdUsuario(int idUsuario) {
		ArrayList<Favorito> listaFavoritos = new ArrayList<Favorito>();
		getAll().forEach(item -> {
			if (item.getIdUsuario() == idUsuario)
				listaFavoritos.add(item);
		});
		return listaFavoritos;
	}

	/**
	 * Verifica si existe la combinación de idUsuario y idPublicacion en los
	 * registros de la tabla Favoritos de la DB
	 * 
	 * @param idUsuario
	 * @param idPublicacion
	 * @return
	 */
	public boolean existeEnRegistrosFavoritos(int idUsuario, int idPublicacion) {
		boolean existeEnLaDB = false;
		existeEnLaDB = getAll().stream()
				.anyMatch(item -> item.getIdUsuario() == idUsuario && item.getIdPublicacion() == idPublicacion);

		return existeEnLaDB;
	}

	public Favorito getObjFavoritoByParams(int idUsuario, int idPublicacion) {
		LOG.info("DAO-Favoritos: Comienza proceso: getObjFavoritoByParams");
		Favorito objFavorito = new Favorito();
		objFavorito = getAll().stream()
				.filter(item -> item.getIdUsuario() == idUsuario && item.getIdPublicacion() == idPublicacion)
				.findFirst().orElse(null);
		// Log de info recuperada
		if (objFavorito == null) {
			LOG.info(String.format("FAV: No se encontró la combinación de idUsuario: %d y idPublicacion: %d", idUsuario,
					idPublicacion));
		} else {
			if (objFavorito.isHabilitado())
				LOG.info(String.format("FAV: está habilitado - idUsuario: %d y idPublicacion: %d", idUsuario,
						idPublicacion));
			else
				LOG.info(String.format("FAV: está deshabilitado - idUsuario: %d y idPublicacion: %d", idUsuario,
						idPublicacion));
		}

		LOG.info("DAO-Favoritos: Se finaliza proceso: getObjFavoritoByParams");
		return objFavorito;
	}

	/// **************** DAO - FUNCIONES GESTION FAVORITOS ****************///
	public boolean guardarNuevoFavorito(int idUsuarioLogueado, int idPublicacion) throws ServidorException {
		LOG.info(String.format("FAV DAO: Nuevo registro idUsuario: %d y idPublicacion: %d", idUsuarioLogueado,
				idPublicacion));
		boolean estadoTransaccion = false;
		// 1- Genera nuevo objeto para ser almacenado en DB
		Favorito objFavorito = new Favorito();
		objFavorito.setIdPublicacion(idPublicacion);
		objFavorito.setIdUsuario(idUsuarioLogueado);
		objFavorito.setHabilitado(true);
		objFavorito.setIdFavorita(this.getCount() + 1);
		// 2- Verifica el estado de la Transaccion con la DB
		estadoTransaccion = this.insert(objFavorito);

		if (!estadoTransaccion) {
			throw new ServidorException("ERROR SQL: Ocurrió un error al guardar en favoritos");
		}
		LOG.info("DAO-Favoritos: Se finaliza proceso: guardarNuevoFavorito");
		return estadoTransaccion;
	}

	public boolean habilitarFavoritoExistente(Favorito objFavorito) throws ServidorException {
		LOG.info(String.format("FAV DAO: Modif. registro idUsuario: %d y idPublicacion: %d", objFavorito.getIdUsuario(),
				objFavorito.getIdPublicacion()));
		boolean estadoTransaccion = false;
		// 1- Setea el valor del objeto para ser almacenado en DB
		objFavorito.setHabilitado(true);
		// 2- Verifica el estado de la Transaccion con la DB
		estadoTransaccion = this.update(objFavorito);
		if (!estadoTransaccion) {
			throw new ServidorException("ERROR SQL: Ocurrió un error al habilitar el estado de favoritos");
		}
		LOG.info("DAO-Favoritos: Se finaliza proceso: habilitarFavoritoExistente");
		return estadoTransaccion;
	}

	public boolean deshabilitarFavoritoExistente(Favorito objFavorito) throws ServidorException {
		LOG.info(String.format("FAV DAO: Modif. registro idUsuario: %d y idPublicacion: %d", objFavorito.getIdUsuario(),
				objFavorito.getIdPublicacion()));
		boolean estadoTransaccion = false;
		// 1- Setea el valor del objeto para ser almacenado en DB
		objFavorito.setHabilitado(false);
		// 2- Verifica el estado de la Transaccion con la DB
		estadoTransaccion = this.update(objFavorito);
		if (!estadoTransaccion) {
			throw new ServidorException("ERROR SQL: Ocurrió un error al deshabilitar el estado de favoritos");
		}
		LOG.info("DAO-Favoritos: Se finaliza proceso: deshabilitarFavoritoExistente");
		return estadoTransaccion;
	}

}
