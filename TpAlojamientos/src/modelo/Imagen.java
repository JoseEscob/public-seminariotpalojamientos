package modelo;

public class Imagen {
	private int idImagen;
	private int idPublicacion;
	private String rutaImgPublicacion;
	private boolean habilitado;

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getRutaImgPublicacion() {
		return rutaImgPublicacion;
	}

	public void setRutaImgPublicacion(String rutaImgPublicacion) {
		this.rutaImgPublicacion = rutaImgPublicacion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}
