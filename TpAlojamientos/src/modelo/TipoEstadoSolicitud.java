package modelo;

public class TipoEstadoSolicitud {
	private int idEstadoSolicitud;
	private String descripcion;
	private boolean habilitado;
	
	public int getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}
	public void setIdEstadoSolicitud(int idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
}
