package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Servicio;

public class Servicios implements Connectable<Servicio> {
	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();

	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4203725714758500524L;
		{
			put("all", "select * from serviciosPublicaciones");
			put("insert", "insert into serviciosPublicaciones set idServicio=?, idPublicacion=?");
			put("count", "select count(*) as cantidad from tiposServicios");
			put("update", "update serviciosPublicaciones set idServicio=? where idPublicacion=? and idServicio=?");
			put("get", "select * from serviciosPublicaciones where idPublicacion=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<Servicio> m;

	@Override
	public ArrayList<Servicio> getAll() {
		cn = new Conexion();
		m = new ArrayList<Servicio>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Servicio o = new Servicio();
				o = readPs_Servicio(rs);
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
	public ArrayList<Servicio> getLike(String like) {
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
	public Servicio get(Servicio obj) {
		cn = new Conexion();
		Servicio o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdServicio());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Servicio();
				o = readPs_Servicio(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Servicio obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdPublicacion());
			ps.setInt(2, obj.getIdServicio());

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
	public boolean update(Servicio obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setInt(1, obj.getIdPublicacion());
			ps.setInt(2, obj.getIdServicio());
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
	public boolean remove(Servicio obj) {
		// No elimina
		return false;
	}

	/// ********************* DAO - FUNCIONES READ/ WRITE ********************** ///
	private Servicio readPs_Servicio(ResultSet rs) throws SQLException {
		Servicio o = new Servicio();
		o.setIdServicio(rs.getInt(cCampo.idServicio));
		o.setIdPublicacion(rs.getInt(cCampo.idPublicacion));
		return o;
	}

	/// ********************* LAMBDA - Métodos de obtención de datos ******** ///

	public Servicio getServicio(int idServicio) {
		Servicio objServicio = new Servicio();

		objServicio = getAll().stream().filter(x -> x.getIdServicio() == idServicio).findFirst().orElse(null);

		return objServicio;
	}

	public ArrayList<Servicio> getAllByIdPublicacion(int idPublicacion) {
		ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
		final TiposServicios tiposServiciosDAO = new TiposServicios();

		getAll().forEach(item -> {
			if (item.getIdPublicacion() == idPublicacion) {
				item.setObjTipoServicio(tiposServiciosDAO.getObjectByIdServicio(item.getIdServicio()));
				listaServicios.add(item);
			}
		});

		return listaServicios;
	}

}
