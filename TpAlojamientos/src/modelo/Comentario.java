package modelo;

import java.sql.Date;

public class Comentario {
	private int idUsuario;
	private int idPublicacion;
	private String descripcion;
	private int puntaje;
	private Date fechaComentario;
	private boolean habilitado;

	// Para la lista de comentarios
	private String rutaFotoPerfilUsuario;
	private String nombreApellidoUsuario;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	// Nuevos getters y setters
	public Date getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public String getRutaFotoPerfilUsuario() {
		return rutaFotoPerfilUsuario;
	}

	public void setRutaFotoPerfilUsuario(String rutaFotoPerfilUsuario) {
		this.rutaFotoPerfilUsuario = rutaFotoPerfilUsuario;
	}

	public String getNombreApellidoUsuario() {
		return nombreApellidoUsuario;
	}

	public void setNombreApellidoUsuario(String nombreApellidoUsuario) {
		this.nombreApellidoUsuario = nombreApellidoUsuario;
	}

}
