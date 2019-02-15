package modelo;

import java.sql.Date;

import controladoresDAO.Usuarios;

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

	private int cantDiasReserva;
	private String nombreApellidoHuesped;
	private String nombreApellidoPropietario;
	private String nombreAlojamientoYZona;

	public void cargarInfoComprobante() {
		Usuarios usuarioDAO = new Usuarios();
		nombreApellidoHuesped = usuarioDAO.getNombreApellidoByIdUsuario(idUsuarioHuesped);
		nombreApellidoPropietario = usuarioDAO.getNombreApellidoByIdUsuario(idUsuarioPropietario);
		//nombreAlojamientoYZona = 
	}

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

	public String getNombreApellidoPropietario() {
		return nombreApellidoPropietario;
	}

	public void setNombreApellidoPropietario(String nombreApellidoPropietario) {
		this.nombreApellidoPropietario = nombreApellidoPropietario;
	}

}
