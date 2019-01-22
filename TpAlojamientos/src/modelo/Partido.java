package modelo;

public class Partido {
	private int idPartido;
	private String nombre;
	private boolean habilitado;
	public int getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}
	public String getNombre() {
		obtenerNombreSinUltCaracter();
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	private void obtenerNombreSinUltCaracter() {
		int ultPosicion = this.nombre.length() - 1;
		if(this.nombre.charAt(ultPosicion) == '?') {
			this.nombre.substring(0, ultPosicion);
		}
	}

}
