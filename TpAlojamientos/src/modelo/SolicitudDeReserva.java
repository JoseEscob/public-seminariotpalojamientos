package modelo;

import java.sql.Date;

public class SolicitudDeReserva {
	private int idSolicitud;
	private int idUsuarioHuesped;
	private int idPublicacion;
	private Date fechaReservaInicio;
	private Date fechaReservaFin;
	private int cantPersonas;
	private int precioFinal;
	private String fechaAltaSolicitud;
	private int idUsuarioPropietario;
	private String fechaDecisionPropietario;
	private String motivoDecisionPropietario;
	private int idEstadoSolicitud;
	private boolean habilitado;

	private String nombreApellidoHuesped;

	private int cantDiasReserva;

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public int getIdUsuarioHuesped() {
		return idUsuarioHuesped;
	}

	public void setIdUsuarioHuesped(int idUsuarioHuesped) {
		this.idUsuarioHuesped = idUsuarioHuesped;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public Date getFechaReservaInicio() {
		return fechaReservaInicio;
	}

	public void setFechaReservaInicio(Date fechaReservaInicio) {
		this.fechaReservaInicio = fechaReservaInicio;
	}

	public Date getFechaReservaFin() {
		return fechaReservaFin;
	}

	public void setFechaReservaFin(Date fechaReservaFin) {
		this.fechaReservaFin = fechaReservaFin;
	}

	public int getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public int getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(int precioFinal) {
		this.precioFinal = precioFinal;
	}

	public String getFechaAltaSolicitud() {
		return fechaAltaSolicitud;
	}

	public void setFechaAltaSolicitud(String fechaAltaSolicitud) {
		this.fechaAltaSolicitud = fechaAltaSolicitud;
	}

	public int getIdUsuarioPropietario() {
		return idUsuarioPropietario;
	}

	public void setIdUsuarioPropietario(int idUsuarioPropietario) {
		this.idUsuarioPropietario = idUsuarioPropietario;
	}

	public String getFechaDecisionPropietario() {
		return fechaDecisionPropietario;
	}

	public void setFechaDecisionPropietario(String fechaDecisionPropietario) {
		this.fechaDecisionPropietario = fechaDecisionPropietario;
	}

	public String getMotivoDecisionPropietario() {
		return motivoDecisionPropietario;
	}

	public void setMotivoDecisionPropietario(String motivoDecisionPropietario) {
		this.motivoDecisionPropietario = motivoDecisionPropietario;
	}

	public int getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}

	public void setIdEstadoSolicitud(int idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public int getCantDiasReserva() {
		return cantDiasReserva;
	}

	public void setCantDiasReserva(int cantDiasReserva) {
		this.cantDiasReserva = cantDiasReserva;
	}

	public String getNombreApellidoHuesped() {
		return nombreApellidoHuesped;
	}

	public void setNombreApellidoHuesped(String nombreApellidoHuesped) {
		this.nombreApellidoHuesped = nombreApellidoHuesped;
	}

}
