package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Solicitud;
import modelo.SolicitudDeReserva;

public class SolicitudesDeReserva implements Connectable<SolicitudDeReserva> {
	private static final _DAOConstantesNombreCampos cCampo = new _DAOConstantesNombreCampos();
	private final static String camposInsertIntoDB = "idUsuarioHuesped=?, idPublicacion=?"
			+ ", fechaReservaInicio=?, fechaReservaFin=?, cantPersonas=?, precioFinal=?, fechaAltaSolicitud="
			+ cCampo.sql_STR_TO_DATE_YmdHiS
			+ ", idUsuarioPropietario=?, fechaDecisionPropietario=?, motivoDecisionPropietario=?, idEstadoSolicitud=?"
			+ ", habilitado=?, idSolicitud=?";

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
	private ArrayList<SolicitudDeReserva> m;

	@Override
	public ArrayList<SolicitudDeReserva> getAll() {
		cn = new Conexion();
		m = new ArrayList<SolicitudDeReserva>();

		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("all"));
			while (rs.next()) {
				SolicitudDeReserva o = new SolicitudDeReserva();
				o = readPs_SolicitudDeReserva(rs);
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
	public ArrayList<SolicitudDeReserva> getLike(String like) {
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
	public SolicitudDeReserva get(SolicitudDeReserva obj) {
		cn = new Conexion();
		SolicitudDeReserva o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdSolicitud());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				o = new SolicitudDeReserva();
				o = readPs_SolicitudDeReserva(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(SolicitudDeReserva obj) {
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
			ps = writePs_SolDeReserva(obj, ps);
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
	public boolean update(SolicitudDeReserva obj) {
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
			ps = writePs_SolDeReserva(obj, ps);
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
	public boolean remove(SolicitudDeReserva obj) {
		SolicitudDeReserva u = new SolicitudDeReserva();
		u.setIdSolicitud(obj.getIdSolicitud());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

	/// ********************* DAO - Métodos READ/ WRITE ********************** ///
	private SolicitudDeReserva readPs_SolicitudDeReserva(ResultSet rs) throws SQLException {
		SolicitudDeReserva o = new SolicitudDeReserva();
		o.setIdSolicitud(rs.getInt(cCampo.idSolicitud));
		o.setIdUsuarioHuesped(rs.getInt(cCampo.idUsuarioHuesped));
		o.setIdPublicacion(rs.getInt(cCampo.idPublicacion));
		o.setFechaReservaInicio(rs.getDate(cCampo.fechaReservaInicio));
		o.setFechaReservaFin(rs.getDate(cCampo.fechaReservaFin));
		o.setCantPersonas(rs.getInt(cCampo.cantPersonas));
		o.setPrecioFinal(rs.getInt(cCampo.precioFinal));
		o.setFechaAltaSolicitud(rs.getString(cCampo.fechaAltaSolicitud));
		o.setIdUsuarioPropietario(rs.getInt(cCampo.idUsuarioPropietario));
		o.setFechaDecisionPropietario(rs.getString(cCampo.fechaDecisionPropietario));
		o.setMotivoDecisionPropietario(rs.getString(cCampo.motivoDecisionPropietario));
		o.setIdEstadoSolicitud(rs.getInt(cCampo.idEstadoSolicitud));
		o.setHabilitado(rs.getBoolean(cCampo.habilitado));
		return o;
	}

	private PreparedStatement writePs_SolDeReserva(SolicitudDeReserva obj, PreparedStatement ps) throws SQLException {
		ps.setInt(1, obj.getIdUsuarioHuesped());
		ps.setInt(2, obj.getIdPublicacion());
		ps.setDate(3, obj.getFechaReservaInicio());
		ps.setDate(4, obj.getFechaReservaFin());
		ps.setInt(5, obj.getCantPersonas());
		ps.setInt(6, obj.getPrecioFinal());
		ps.setString(7, obj.getFechaAltaSolicitud());
		ps.setInt(8, obj.getIdUsuarioPropietario());
		ps.setString(9, obj.getFechaDecisionPropietario());
		ps.setString(10, obj.getMotivoDecisionPropietario());
		ps.setInt(11, obj.getIdEstadoSolicitud());
		ps.setBoolean(12, obj.isHabilitado());
		ps.setInt(13, obj.getIdSolicitud());
		return ps;
	}

	/// ********************* LAMBDA - Métodos de obtención de datos ******** ///
	public ArrayList<SolicitudDeReserva> getAllByIdUsuarioHuesped(int idUsuarioHuesped) {
		ArrayList<SolicitudDeReserva> listaFiltrada = new ArrayList<SolicitudDeReserva>();

		getAll().forEach(item -> {
			if (item.getIdUsuarioHuesped() == idUsuarioHuesped)
				listaFiltrada.add(item);
		});

		return listaFiltrada;
	}

	public ArrayList<SolicitudDeReserva> getAllByIdUsuarioPropietario(int idUsuarioPropietario) {
		ArrayList<SolicitudDeReserva> listaFiltrada = new ArrayList<SolicitudDeReserva>();

		getAll().forEach(item -> {
			if (item.getIdUsuarioPropietario() == idUsuarioPropietario)
				listaFiltrada.add(item);
		});

		return listaFiltrada;
	}
}
