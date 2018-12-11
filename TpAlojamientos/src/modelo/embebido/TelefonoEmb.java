package modelo.embebido;

public class TelefonoEmb {
	private int idTelefono;
	private PublicacionEmb idPublicacion;
	private String numero;
	private boolean habilitado;
	public int getIdTelefono() {
		return idTelefono;
	}
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}
	public PublicacionEmb getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(PublicacionEmb idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	
	
}
