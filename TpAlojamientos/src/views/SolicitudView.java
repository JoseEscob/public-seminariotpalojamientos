package views;

import modelo.Publicacion;
import modelo.Usuario;
import modelo.TipoEstadoSolicitud;
import modelo.Solicitud;

import exceptions.CargaViewException;

public class SolicitudView {
	
	private Solicitud solicitud;
	private TipoEstadoSolicitud estadoSolicitud;
	private Usuario usuario;
	private PublicacionView publicacion;
	
	public SolicitudView() {
		
	}
	
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public TipoEstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(TipoEstadoSolicitud estadoSolicitud)throws CargaViewException {
		if(estadoSolicitud == null)
			throw new CargaViewException("El parametro de la funcion setEstadoSolicitud de SolicitudView es nulo");
		this.estadoSolicitud = estadoSolicitud;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) throws CargaViewException {
		if(usuario == null)
			throw new CargaViewException("El parametro de la funcion setUsuario de SolicitudView es nulo");
		this.usuario = usuario;
	}
	public PublicacionView getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(PublicacionView publicacion)throws CargaViewException {
		if(publicacion == null)
			throw new CargaViewException("El parametro de la funcion setPublicacion de SolicitudView es nulo");
		this.publicacion = publicacion;
	}
	
	

}
