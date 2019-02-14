package views;

import controladoresDAO.Imagenes;
import controladoresDAO.Localidades;
import controladoresDAO.Publicaciones;
import extra.Constantes;
import modelo.Localidad;

/**
 * Clase para obtener info adicional de una publicación
 * 
 * @author José
 *
 */
public class PublicacionInfo {
	private String publicacionRutaPrimerImagen;
	private String publicacionPartidoLocalidad;

	public void cargarDatosDePublicacion(int idPublicacion, int idLocalidad) {
		String rutaPrimerImagen = new Imagenes().getPathOfFirstIMGByIdPublicacion(idPublicacion);

		String publicacionPartidoLocalidad;
		//int idLocalidad = new Publicaciones().getObjectByID(idPublicacion).getIdLocalidad();
		Localidad objLocalidad = new Localidades().getLocalidadById(idLocalidad);
		objLocalidad.setNombrePartido(new Localidades().getNombrePartido(objLocalidad.getIdPartido()));
		publicacionPartidoLocalidad = String.format("%s, %s", objLocalidad.getNombre(),
				objLocalidad.getNombrePartido());

		if (rutaPrimerImagen != null)
			this.setPublicacionRutaPrimerImagen(rutaPrimerImagen);
		else
			this.setPublicacionRutaPrimerImagen(Constantes.RUTAhomeNoPhoto);

		this.setPublicacionPartidoLocalidad(publicacionPartidoLocalidad);
	}

	public String getPublicacionRutaPrimerImagen() {
		return publicacionRutaPrimerImagen;
	}

	public void setPublicacionRutaPrimerImagen(String publicacionRutaPrimerImagen) {
		this.publicacionRutaPrimerImagen = publicacionRutaPrimerImagen;
	}

	public String getPublicacionPartidoLocalidad() {
		return publicacionPartidoLocalidad;
	}

	public void setPublicacionPartidoLocalidad(String publicacionPartidoLocalidad) {
		this.publicacionPartidoLocalidad = publicacionPartidoLocalidad;
	}
}
