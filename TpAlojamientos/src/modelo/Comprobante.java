package modelo;

import java.sql.Date;

public class Comprobante {
	private int idComprobante;
	private int idSolicitud;
	private int idUsuarioHuesped;
	private int idPublicacion;
	private Date fechaReservaInicio;
	private Date fechaReservaFin;
	private int cantPersonas;
	private int precioFinal;
	private Date fechaAlta;
	private int idUsuarioPropietario;
	private boolean habilitado;

	public int getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
	}

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

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getIdUsuarioPropietario() {
		return idUsuarioPropietario;
	}

	public void setIdUsuarioPropietario(int idUsuarioPropietario) {
		this.idUsuarioPropietario = idUsuarioPropietario;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}
