package views;

import java.util.ArrayList;
import modelo.Usuario;
import modelo.Favorito;
import modelo.Imagen;
import modelo.Localidad;
import modelo.Publicacion;
import modelo.Servicio;
import exceptions.CargaViewException;

/**
 * Prototipo 1
 */
public class PublicacionView {
	private Publicacion publicacion;
	private Usuario usuario;
	private ArrayList<Imagen> imagenes;
	private int cantComentarios;
	private ArrayList<String> listaRutaImg;
	private Localidad objLocalidad;
	private Favorito objFavorito;
	private ArrayList<Servicio> listaServicios;
	private String descripcionTipoAlojamiento;

	public PublicacionView() {
		this.setCantComentarios(0);
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

	public ArrayList<String> getListaRutaImg() {
		return listaRutaImg;
	}

	public void setListaRutaImg(ArrayList<String> listaRutaImg) {
		this.listaRutaImg = listaRutaImg;
	}

	public Favorito getObjFavorito() {
		return objFavorito;
	}

	public void setObjFavorito(Favorito objFavorito) {
		this.objFavorito = objFavorito;
	}

	public Localidad getObjLocalidad() {
		return objLocalidad;
	}

	public void setObjLocalidad(Localidad objLocalidad) {
		this.objLocalidad = objLocalidad;
	}

	public ArrayList<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public String getDescripcionTipoAlojamiento() {
		return descripcionTipoAlojamiento;
	}

	public void setDescripcionTipoAlojamiento(String descripcionTipoAlojamiento) {
		this.descripcionTipoAlojamiento = descripcionTipoAlojamiento;
	}

}
