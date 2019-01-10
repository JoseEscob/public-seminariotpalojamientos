package views;

import java.util.ArrayList;
import modelo.Usuario;
import modelo.Imagen;
import modelo.Publicacion;
/**
 * Prototipo 1
 * */
public class PublicacionView {
		private Publicacion publicacion;
		private Usuario usuario;
		private ArrayList<Imagen> imagenes;
		private int comentarios;
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
		public int getComentarios() {
			return comentarios;
		}
		public void setComentarios(int comentarios) {
			this.comentarios = comentarios;
		}
	
	
		
}
