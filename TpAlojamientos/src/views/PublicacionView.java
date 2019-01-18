package views;

import java.util.ArrayList;
import modelo.Usuario;
import modelo.Imagen;
import modelo.Publicacion;
import exceptions.CargaViewException;

/**
 * Prototipo 1
 */
public class PublicacionView {
	private Publicacion publicacion;
	private Usuario usuario;
	private ArrayList<Imagen> imagenes;
	private int cantComentarios;

	public PublicacionView() {

	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(ArrayList<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public int getCantComentarios() {
		return cantComentarios;
	}

	public void setCantComentarios(int cantComentarios) {
		this.cantComentarios = cantComentarios;
	}

	@Deprecated
	public void cargarImagenes(ArrayList<Imagen> imagenes) throws CargaViewException {

		if (imagenes == null)
			throw new CargaViewException("El parametro de la funcion cargarImagenes de PublicacionView es nulo");

		ArrayList<Imagen> temp = new ArrayList<Imagen>();
		imagenes.forEach(item -> {
			if (item.getIdPublicacion() == this.publicacion.getIdPublicacion())
				temp.add(item);
		});
		this.setImagenes(temp);

	}

}
