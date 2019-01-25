package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.TipoServicio;

public class TiposServicios implements Connectable<TipoServicio> {
	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();
	/*
	 * idTipoAlojamiento int not null auto_increment, descripcion varchar(50) not
	 * null, habilitado tinyint(1) not null default 1,
	 */

	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4203725714758500524L;
		{
			put("all", "select * from tiposServicios");
			put("insert", "insert into tiposServicios set descripcion=?, idTipoServicio=?, idServicio=?");
			put("count", "select count(*) as cantidad from tiposServicios");
			put("update", "update tiposServicios set descripcion=?, idTipoServicio=? where idServicio=?");
			put("get", "select * from tiposServicios where idServicio=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<TipoServicio> m;

	@Override
	public ArrayList<TipoServicio> getAll() {
		cn = new Conexion();
		m = new ArrayList<TipoServicio>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				TipoServicio o = new TipoServicio();
				o = readPs_TipoServicio(rs);
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
	public ArrayList<TipoServicio> getLike(String like) {
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
	public TipoServicio get(TipoServicio obj) {
		cn = new Conexion();
		TipoServicio o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdTipoServicio());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new TipoServicio();
				o = readPs_TipoServicio(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(TipoServicio obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setString(1, obj.getDescripcion());
			ps.setInt(2, obj.getIdTipoServicio());
			ps.setInt(3, obj.getIdServicio());
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
	public boolean update(TipoServicio obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getDescripcion());
			ps.setInt(2, obj.getIdTipoServicio());
			ps.setInt(3, obj.getIdServicio());
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
	public boolean remove(TipoServicio obj) {
		// No elimina
		return false;
	}

	/// ********************* DAO - FUNCIONES READ/ WRITE ********************** ///
	private TipoServicio readPs_TipoServicio(ResultSet rs) throws SQLException {
		TipoServicio o = new TipoServicio();
		o.setIdServicio(rs.getInt(cCampo.idServicio));
		o.setIdTipoServicio(rs.getInt(cCampo.idTipoServicio));
		o.setDescripcion(rs.getString(cCampo.descripcion));
		return o;
	}

	/// ********************* LAMBDA - Métodos de obtención de datos ******** ///

	public TipoServicio getTipoServicio(int idTipoServicio) {
		TipoServicio objTipoServicio = new TipoServicio();

		objTipoServicio = getAll().stream().filter(x -> x.getIdTipoServicio() == idTipoServicio).findFirst()
				.orElse(null);

		return objTipoServicio;
	}

	public TipoServicio getObjectByIdServicio(int idServicio) {
		TipoServicio objTipoServicio = new TipoServicio();

		objTipoServicio = getAll().stream().filter(x -> x.getIdServicio() == idServicio).findFirst().orElse(null);

		return objTipoServicio;
	}

	public ArrayList<TipoServicio> getAllByIdServicio(int idServicio) {
		ArrayList<TipoServicio> listaTipoServicio = new ArrayList<TipoServicio>();

		getAll().forEach(item -> {
			if (item.getIdServicio() == idServicio)
				listaTipoServicio.add(item);
		});
		return listaTipoServicio;
	}

}
