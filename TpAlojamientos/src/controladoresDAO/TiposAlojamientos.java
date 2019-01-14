package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.TipoAlojamiento;

public class TiposAlojamientos implements Connectable<TipoAlojamiento> {

	/*
	 * idTipoAlojamiento int not null auto_increment, descripcion varchar(50) not
	 * null, habilitado tinyint(1) not null default 1,
	 */

	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4203725714708500524L;

		{
			put("all", "select * from tiposalojamientos");
			put("insert", "insert into tiposalojamientos values(null,?,default)");
			put("count", "select count(*) as cantidad from tiposalojamientos");
			put("update", "update tiposalojamientos set descripcion=?, habilitado=? where idTipoAlojamiento=?");
			put("get", "select * from tiposalojamientos where idTipoAlojamiento=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<TipoAlojamiento> m;

	@Override
	public ArrayList<TipoAlojamiento> getAll() {
		cn = new Conexion();
		m = new ArrayList<TipoAlojamiento>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				TipoAlojamiento o = new TipoAlojamiento();
				o.setIdTipoAlojamiento(rs.getInt(1));
				o.setDescripcion(rs.getString(2));
				o.setHabilitado(rs.getBoolean(3));
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
	public ArrayList<TipoAlojamiento> getLike(String like) {
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
	public TipoAlojamiento get(TipoAlojamiento obj) {
		cn = new Conexion();
		TipoAlojamiento o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdTipoAlojamiento());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new TipoAlojamiento();
				o.setIdTipoAlojamiento(rs.getInt(1));
				o.setDescripcion(rs.getString(2));
				o.setHabilitado(rs.getBoolean(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(TipoAlojamiento obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setString(1, obj.getDescripcion());
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
	public boolean update(TipoAlojamiento obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getDescripcion());
			ps.setBoolean(2, obj.isHabilitado());
			ps.setInt(3, obj.getIdTipoAlojamiento());
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
	public boolean remove(TipoAlojamiento obj) {
		TipoAlojamiento u = new TipoAlojamiento();
		u.setIdTipoAlojamiento(obj.getIdTipoAlojamiento());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* LAMBDA - Métodos de obtención de datos ******** ///

	public TipoAlojamiento getTipoAlojamiento(int idTipoAlojamiento) {
		TipoAlojamiento objTipoAlojamiento = new TipoAlojamiento();

		objTipoAlojamiento = getAll().stream().filter(x -> x.getIdTipoAlojamiento() == idTipoAlojamiento).findFirst()
				.orElse(null);

		return objTipoAlojamiento;
	}
}
