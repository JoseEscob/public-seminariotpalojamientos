package modelo;

public class TipoAlojamiento {
	private int idTipoAlojamiento;
	private String descripcion;
	private boolean habilitado;
	public int getIdTipoAlojamiento() {
		return idTipoAlojamiento;
	}
	public void setIdTipoAlojamiento(int idTipoAlojamiento) {
		this.idTipoAlojamiento = idTipoAlojamiento;
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
