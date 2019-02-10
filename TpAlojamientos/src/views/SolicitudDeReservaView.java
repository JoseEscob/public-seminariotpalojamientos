package views;

import controladoresDAO.Imagenes;
import controladoresDAO.Localidades;
import controladoresDAO.Publicaciones;
import extra.Constantes;
import modelo.Localidad;
import modelo.Publicacion;
import modelo.SolicitudDeReserva;

public class SolicitudDeReservaView {
	private SolicitudDeReserva objSolReserva = new SolicitudDeReserva();
	private String publicacionRutaPrimerImagen;
	private String publicacionNombre;
	private String publicacionPartidoLocalidad;

	public SolicitudDeReserva getObjSolReserva() {
		return objSolReserva;
	}

	public void setObjSolReserva(SolicitudDeReserva objSolDeReserva) {
		this.objSolReserva = objSolDeReserva;
	}

	public String getPublicacionRutaPrimerImagen() {
		return publicacionRutaPrimerImagen;
	}

	public void setPublicacionRutaPrimerImagen(String publicacionRutaPrimerImagen) {
		this.publicacionRutaPrimerImagen = publicacionRutaPrimerImagen;
	}

	public String getPublicacionNombre() {
		return publicacionNombre;
	}

	public void setPublicacionNombre(String publicacionNombre) {
		this.publicacionNombre = publicacionNombre;
	}

	public String getPublicacionPartidoLocalidad() {
		return publicacionPartidoLocalidad;
	}

	public void setPublicacionPartidoLocalidad(String publicacionPartidoLocalidad) {
		this.publicacionPartidoLocalidad = publicacionPartidoLocalidad;
	}

	public void cargarDatosDePublicacion(int idPublicacion) {
		Publicacion objPublicacion = new Publicaciones().getObjectByID(idPublicacion);
		String rutaPrimerImagen = new Imagenes().getPathOfFirstIMGByIdPublicacion(idPublicacion);
		String publicacionNombre = objPublicacion.getNombre();
		String publicacionPartidoLocalidad;
		Localidad objLocalidad = new Localidades().getLocalidadById(objPublicacion.getIdLocalidad());
		objLocalidad.setNombrePartido(new Localidades().getNombrePartido(objLocalidad.getIdPartido()));

		publicacionPartidoLocalidad = String.format("%s, %s", objLocalidad.getNombre(),
				objLocalidad.getNombrePartido());

		if (rutaPrimerImagen != null)
			this.setPublicacionRutaPrimerImagen(rutaPrimerImagen);
		else
			this.setPublicacionRutaPrimerImagen(Constantes.RUTAhomeNoPhoto);

		this.setPublicacionNombre(publicacionNombre);
		this.setPublicacionPartidoLocalidad(publicacionPartidoLocalidad);
	}
}
