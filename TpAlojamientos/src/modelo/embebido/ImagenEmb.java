package modelo.embebido;

public class ImagenEmb {
	private int idImagen;
	private PublicacionEmb publicacion;
	private String ruta;
	private boolean habilitado;
	public int getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
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
	
	
}
