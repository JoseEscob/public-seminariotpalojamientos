package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Comprobante;
import modelo.Solicitud;

public class Comprobantes implements Connectable<Comprobante> {
	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();
	private final static String camposInsertIntoDB = "idSolicitud=?, idUsuarioHuesped=?, idPublicacion=?"
			+ ", fechaReservaInicio=?, fechaReservaFin=?, cantPersonas=?, precioFinal=?, fechaAlta=?"
			+ ", idUsuarioPropietario=?, habilitado=?";

	private static HashMap<String, String> queries = new HashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 8651650972239809763L;

		{
			put("all", "select * from comprobantes");
			put("insert", String.format("insert into comprobantes set %s , idComprobante=?", camposInsertIntoDB));
			put("count", "select count(*) as cantidad from comprobantes");
			put("update", String.format("update comprobantes set %s where idComprobante=?", camposInsertIntoDB));
			put("get", "select * from comprobantes where idComprobante=?");
			put("like", "");

		}
	};

	private Conexion cn;
	private ArrayList<Comprobante> m;

	@Override
	public ArrayList<Comprobante> getAll() {
		cn = new Conexion();
		m = new ArrayList<Comprobante>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				Comprobante o = new Comprobante();
				o = readPs_Comprobante(rs);
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
	public ArrayList<Comprobante> getLike(String like) {
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
	public Comprobante get(Comprobante obj) {
		cn = new Conexion();
		Comprobante o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdSolicitud());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new Comprobante();
				o = readPs_Comprobante(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Comprobante obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		;
		try {

			Solicitudes solicitudes = new Solicitudes();
			Solicitud solicitud = new Solicitud();
			solicitud.setIdSolicitud(obj.getIdSolicitud());
			if (solicitudes.get(solicitud) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps = writePs_Comprobante(obj, ps);
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
	public boolean update(Comprobante obj) {
		if (obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {

			Solicitudes solicitudes = new Solicitudes();
			Solicitud solicitud = new Solicitud();
			solicitud.setIdSolicitud(obj.getIdSolicitud());
			if (solicitudes.get(solicitud) == null)
				return false;

			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps = writePs_Comprobante(obj, ps);
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
	public boolean remove(Comprobante obj) {
		Comprobante u = new Comprobante();
		u.setIdComprobante(obj.getIdComprobante());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* DAO - Métodos READ/ WRITE ********************** ///
	private Comprobante readPs_Comprobante(ResultSet rs) throws SQLException {
		Comprobante o = new Comprobante();
		o.setIdComprobante(rs.getInt(cCampo.idComprobante));
		o.setIdSolicitud(rs.getInt(cCampo.idSolicitud));
		o.setIdUsuarioHuesped(rs.getInt(cCampo.idUsuarioHuesped));
		o.setIdPublicacion(rs.getInt(cCampo.idPublicacion));
		o.setFechaReservaInicio(rs.getDate(cCampo.fechaReservaInicio));
		o.setFechaReservaFin(rs.getDate(cCampo.fechaReservaFin));
		o.setCantPersonas(rs.getInt(cCampo.cantPersonas));
		o.setPrecioFinal(rs.getInt(cCampo.precioFinal));
		o.setFechaAlta(rs.getDate(cCampo.fechaAlta));
		o.setIdUsuarioPropietario(rs.getInt(cCampo.idUsuarioPropietario));
		o.setHabilitado(rs.getBoolean(cCampo.habilitado));
		return o;
	}

	private PreparedStatement writePs_Comprobante(Comprobante obj, PreparedStatement ps) throws SQLException {
		ps.setInt(1, obj.getIdSolicitud());
		ps.setInt(2, obj.getIdUsuarioHuesped());
		ps.setInt(3, obj.getIdPublicacion());
		ps.setDate(4, obj.getFechaReservaInicio());
		ps.setDate(5, obj.getFechaReservaFin());
		ps.setInt(6, obj.getCantPersonas());
		ps.setInt(7, obj.getPrecioFinal());
		ps.setDate(8, obj.getFechaAlta());
		ps.setInt(9, obj.getIdUsuarioPropietario());
		ps.setBoolean(10, obj.isHabilitado());
		ps.setInt(11, obj.getIdComprobante());
		return ps;
	}

	/// ********************* LAMBDA - Métodos de obtención de datos ******** ///
	public ArrayList<Comprobante> getAllByIdUsuarioHuesped(int idUsuarioHuesped) {
		ArrayList<Comprobante> listaFiltrada = new ArrayList<Comprobante>();

		getAll().forEach(item -> {
			if (item.getIdUsuarioHuesped() == idUsuarioHuesped)
				listaFiltrada.add(item);
		});

		return listaFiltrada;
	}

	public ArrayList<Comprobante> getAllByIdUsuarioPropietario(int idUsuarioPropietario) {
		ArrayList<Comprobante> listaFiltrada = new ArrayList<Comprobante>();

		getAll().forEach(item -> {
			if (item.getIdUsuarioPropietario() == idUsuarioPropietario)
				listaFiltrada.add(item);
		});

		return listaFiltrada;
	}
}
