package views;

import modelo.Comentario;
import modelo.Usuario;

public class ComentarioView {
	private Comentario comentario;
	private Usuario usuario;
	
	public ComentarioView() {
		
	}
	
	public ComentarioView(Comentario comentario, Usuario usuario) {
		this.comentario = comentario;
		this.usuario = usuario;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
