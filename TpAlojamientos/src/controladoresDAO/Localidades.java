package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Localidad;
import modelo.Partido;

public class Localidades implements Connectable<Localidad> {

	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 5716418584259670866L;

		{
			put("all", "select * from localidades");
			put("insert", "insert into localidades values(null,?,?,?,default)");
			put("count", "select count(*) as cantidad from localidades");
			put("update",
					"update localidades set idPartido=?, nombre=?, codPostal=?, habilitado=? where idLocalidad=?");
			put("get", "select * from localidades where idLocalidad=?");
			put("like", "");
		}
	};

	private Conexion cn;
	private ArrayList<Localidad> m;

	@Override
	public ArrayList<Localidad> getAll() {
		cn = new Conexion();
		m = new ArrayList<Localidad>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Localidad o = new Localidad();
				o.setIdLocalidad(rs.getInt(1));
				o.setIdPartido(rs.getInt(2));
				o.setNombre(rs.getString(3));
				o.setCodPostal(rs.getInt(4));
				o.setHabilitado(rs.getBoolean(5));
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
	public ArrayList<Localidad> getLike(String like) {
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
	public Localidad get(Localidad obj) {
		cn = new Conexion();
		Localidad o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdLocalidad());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Localidad();
				o.setIdLocalidad(rs.getInt(1));
				o.setIdPartido(rs.getInt(2));
				o.setNombre(rs.getString(3));
				o.setCodPostal(rs.getInt(4));
				o.setHabilitado(rs.getBoolean(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Localidad obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {
			// compruebo foreign key
			Partido p = new Partido();
			Partidos pa = new Partidos();
			p.setIdPartido(obj.getIdPartido());
			if (pa.get(p) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdPartido());
			ps.setString(1, obj.getNombre());
			ps.setInt(1, obj.getCodPostal());
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
	public boolean update(Localidad obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {

			// compruebo foreign key
			Partido p = new Partido();
			Partidos pa = new Partidos();
			p.setIdPartido(obj.getIdPartido());
			if (pa.get(p) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setInt(1, obj.getIdPartido());
			ps.setString(2, obj.getNombre());
			ps.setInt(3, obj.getCodPostal());
			ps.setBoolean(4, obj.isHabilitado());
			ps.setInt(5, obj.getIdLocalidad());
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
	public boolean remove(Localidad obj) {
		Localidad u = new Localidad();
		u.setIdLocalidad(obj.getIdLocalidad());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* LAMBDA - Métodos de obtención de datos ******** ///
	public ArrayList<Localidad> getAllByIdPartido(int idPartido) {
		ArrayList<Localidad> listaLocalidadesFiltrada = new ArrayList<Localidad>();

		getAll().forEach(item -> {
			if (item.getIdPartido() == idPartido)
				listaLocalidadesFiltrada.add(item);
		});

		return listaLocalidadesFiltrada;
	}

}
