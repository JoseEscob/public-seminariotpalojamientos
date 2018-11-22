package controllersServlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.Usuarios;
import modelo.Usuario;

/**
 * Servlet implementation class IniciarSesionServlet
 */
@WebServlet("/IniciarSesionServlet")
public class IniciarSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0- declaracion de variables locales
		String paginaJsp = "/Inicio.jsp";
		Usuarios usuarios = new Usuarios();
		Usuario usr = new Usuario();
		ArrayList<Usuario> alles = new ArrayList<Usuario>();
		boolean existe = false;
		// 1- recuperar valores del formulario JSP
		String nombreUsuario = request.getParameter("txtUser").toString();
		String claveUsuario = request.getParameter("txtPass").toString();
		// 2- validar información obtenida JSP
		if(nombreUsuario.trim().length() != 0) {
			if(claveUsuario.trim().length() != 0) {
				alles = usuarios.getAll();
				for(Usuario usuario : alles) {
					if(usuario.getNombre().compareTo(nombreUsuario) == 0) {
						if(usuario.getClaveUsuario().compareTo(claveUsuario) == 0) {
							usr = usuario;
							existe = true;
							break;
						}
					}
				}
			}
		}
		// 3- guardar información validada DAO
		//--no necesario--
		// 4- verificar correcto almacenamiento en DB
		// SE ALMACENA LA VARIABLE SESSION
		request.getSession().setAttribute("usuario", usr);
		// 5- Informar estado
		request.getRequestDispatcher(paginaJsp).forward(request,response);

	}

}
