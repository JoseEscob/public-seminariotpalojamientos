package extra;

import javax.servlet.http.HttpServletRequest;
import modelo.Usuario;

public class ORSesion {

	public static Usuario getUsuarioBySession(HttpServletRequest request) {
		return (Usuario) request.getSession().getAttribute(Constantes.sessionUser);
	}
	
	public static boolean sesionActiva(HttpServletRequest request) {
		return request.getSession().getAttribute(Constantes.sessionUser) == null ? false : true;
	}
	
	public static void cerrarSesion(HttpServletRequest request) {
		request.getSession().removeAttribute(Constantes.sessionUser);
		request.getSession().invalidate();
	}
	
	public static void nuevaSesion(HttpServletRequest request, Usuario usr) {
		request.getSession().setAttribute(Constantes.sessionUser, usr);
	}
}
