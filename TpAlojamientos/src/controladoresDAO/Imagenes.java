package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import extra.Constantes;
import modelo.Imagen;
import modelo.Publicacion;

public class Imagenes implements Connectable<Imagen> {
	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();

	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1494952309320462666L;

		{
			put("all", "select * from imagenes");
			put("insert", "insert into imagenes values(?,?,?,default)");
			put("count", "select count(*) as cantidad from imagenes");
			put("update", "update imagenes set " + cCampo.rutaImgPublicacion + "=?, " + cCampo.habilitado + "=? where "
					+ cCampo.idImagen + "=? and " + cCampo.idPublicacion + "=?");
			put("get", "select * from imagenes where " + cCampo.idImagen + "=? and " + cCampo.idPublicacion + "=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<Imagen> m;

	@Override
	public ArrayList<Imagen> getAll() {
		cn = new Conexion();
		m = new ArrayList<Imagen>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Imagen o = new Imagen();
				o = readPS_Imagen(rs);
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
	public ArrayList<Imagen> getLike(String like) {
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
	public Imagen get(Imagen obj) {
		cn = new Conexion();
		Imagen o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdImagen());
			ps.setInt(2, obj.getIdPublicacion());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Imagen();
				o = readPS_Imagen(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Imagen obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {

			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if (publicaciones.get(publicacion) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdImagen());
			ps.setInt(2, obj.getIdPublicacion());
			ps.setString(3, obj.getRutaImgPublicacion());
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
	public boolean update(Imagen obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {

			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if (publicaciones.get(publicacion) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getRutaImgPublicacion());
			ps.setBoolean(2, obj.isHabilitado());
			ps.setInt(3, obj.getIdImagen());
			ps.setInt(4, obj.getIdPublicacion());
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
	public boolean remove(Imagen obj) {
		Imagen u = new Imagen();
		u.setIdImagen(obj.getIdImagen());
		u.setIdPublicacion(obj.getIdPublicacion());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* DAO - FUNCIONES READ/ WRITE ********************** ///
	private Imagen readPS_Imagen(ResultSet rs) throws SQLException {
		Imagen o = new Imagen();
		o.setIdImagen(rs.getInt(cCampo.idImagen));
		o.setIdPublicacion(rs.getInt(cCampo.idPublicacion));
		o.setRutaImgPublicacion(rs.getString(cCampo.rutaImgPublicacion));
		o.setHabilitado(rs.getBoolean(cCampo.habilitado));
		// --- Auto Set
		if (o.getRutaImgPublicacion().isEmpty())
			o.setRutaImgPublicacion(Constantes.RUTAhomeNoPhoto);
		return o;
	}

	/// ********************* LAMBDA - Funciones de obtención de datos ******** ///
	public ArrayList<Imagen> getAllByIdPublicacion(int idPublicacion) {
		ArrayList<Imagen> listaImagenes = new ArrayList<Imagen>();
		getAll().forEach(item -> {
			if (item.getIdPublicacion() == idPublicacion)
				listaImagenes.add(item);
		});
		return listaImagenes;
	}

	public String getPathOfFirstIMGByIdPublicacion(int idPublicacion) {
		// Imagen objImagen = getAll().stream().filter(item -> item.getIdPublicacion()
		// == idPublicacion).findFirst()
		// .orElse(null);

		// return objImagen.getRutaImgPublicacion();

		if (getAllByIdPublicacion(idPublicacion).isEmpty())
			return null;
		else
			return getAllByIdPublicacion(idPublicacion).get(0).getRutaImgPublicacion();
	}

}
