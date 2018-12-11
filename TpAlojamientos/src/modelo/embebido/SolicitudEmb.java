package modelo.embebido;

import java.sql.Date;

import modelo.TipoEstadoSolicitud;
import modelo.Usuario;

public class SolicitudEmb {
	private int idSolicitud;
	private Usuario usuario;
	private PublicacionEmb publicacion;
	private Date fechaAlta;
	private Date fechaConfirmacion;
	private Date fechaBaja;
	private int cantDias;
	private boolean esDeReserva;
	private TipoEstadoSolicitud estadoSolicitud;
	private boolean habilitado;
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public Usuario getIdUsuario() {
		return usuario;
	}
	public void setIdUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}
	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public int getCantDias() {
		return cantDias;
	}
	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}
	public boolean isEsDeReserva() {
		return esDeReserva;
	}
	public void setEsDeReserva(boolean esDeReserva) {
		this.esDeReserva = esDeReserva;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public PublicacionEmb getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(PublicacionEmb publicacion) {
		this.publicacion = publicacion;
	}
	public TipoEstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(TipoEstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	
	

}
