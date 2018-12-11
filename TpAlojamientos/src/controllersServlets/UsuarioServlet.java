package controllersServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.Usuarios;
import exceptions.ValidacionException;
import extra.Constantes;
import extra.LOG;
import extra.Utilitario;
import modelo.Usuario;
import controladoresDAO.Publicaciones;
import modelo.Publicacion;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;// "/UsuarioAlta.jsp";
	private final Usuarios usuarioDAO = new Usuarios();
	private Publicaciones publicacionDAO = new Publicaciones();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// por <a> href pasa por GET. sino Usar submit para POST
		// VerInfoUsuario(request, response);

		String action = request.getParameter("accion");
		LOG.info("JSP - Acción - GET Banner: " + action);
		if (action != null) {
			switch (action) {
			case "MiPerfil":
				verInfoUsuario(request, response);
				break;
			case "Logout":
				cerrarSesion(request, response);
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("buscarAction");
		LOG.info("JSP - Acción: " + action);
		if (action != null) {
			switch (action) {
			case "login":
				loginUsuario(request, response);
				break;
			case "nuevo":
				altaUsuario(request, response);
				break;
			case "editar":
				// buscarPorNombre(request, response);
				break;
			case "verInfoUsuario":
				verInfoUsuario(request, response);
				break;
			}
		}
	}

	private void altaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario obj = new Usuario();
		String message = null;

		try {
			// 1- recuperar valores del formulario JSP
			String mail = request.getParameter("mail").toString();
			String claveUno = request.getParameter("claveUno").toString();
			String claveDos = request.getParameter("claveDos").toString();
			String nombre = request.getParameter("nombre").toString();
			String apellido = request.getParameter("apellido").toString();
			String fechaNac = request.getParameter("fechaNac");
			boolean sexo = Boolean.valueOf(request.getParameter("sexo"));
			String dni = request.getParameter("dni").toString();
			// String telefono = request.getParameter("telefono").toString();

			// 2- validar información obtenida JSP
			if (!claveUno.equals(claveDos)) {
				throw new ValidacionException("Las claves son diferentes. Por favor revisar que sean identicas");
			}
			if (!Utilitario.esMayorDeEdad(fechaNac)) {
				throw new ValidacionException("El usuario debe ser mayor de edad");
			}
			// 2.2 Validar con la DB
			usuarioDAO.validarCamposUnicos(obj);
			// 3- guardar información validada
			obj.setMail(mail);
			obj.setClaveUsuario(claveUno);
			obj.setNombre(nombre);
			obj.setApellido(apellido);
			obj.setFechaNac(Utilitario.textoAFechaSQL(fechaNac));
			obj.setSexo(sexo);
			obj.setDni(dni);// obj.setTelefono(telefono);
			// 4- verificar correcto almacenamiento en DB
			if (!usuarioDAO.insert(obj))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");
			// ÉXITO
			message = Constantes.REGISTROEXITOSO;

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);
			// response.getWriter().append("<script>alert(" + message + ")</script>");

			paginaJsp = "/UsuarioAlta.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// ********* VER DATOS
	private void verInfoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario user = null;
		String message = null;

		try {
			// 1- Recuperar valores del formulario JSP

			// HttpSession session = request.getSession();
			// String idUsuario = null;// getParameter("txtHiddenIdUsuario")
			user = (Usuario) request.getSession().getAttribute(Constantes.sessionUser);

			// 2- Validar información obtenida JSP
			if (user == null) {
				message = "ERROR: No se pudo recuperar la variable Session: " + Constantes.sessionUser;
				LOG.info(message);
				throw new ValidacionException(message);
			}
			// 3- Recuperar info de la DB
			if (usuarioDAO.get(user) == null)
				throw new ValidacionException(
						"SQL: Ocurrió un error al recuperar usuario con id" + user.getIdUsuario());
			// 4- Devolver información recuperada a la jsp
			request.setAttribute("user", user);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);

			request.setAttribute("rutaDefaultFoto", Constantes.RUTAuserNoPhoto);
			paginaJsp = "/UsuarioViewModif.jsp";
			// response.sendRedirect(request.getContextPath()+ paginaJsp);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// ********* LOGIN ************* //

	private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- declaracion de variables locales
		String message = null;

		Usuario usr = new Usuario();
		ArrayList<Usuario> alles = new ArrayList<Usuario>();
		boolean existe = false;
		try {
			// 1- recuperar valores del formulario JSP
			String correoUsuario = request.getParameter("txtUser").toString();
			String claveUsuario = request.getParameter("txtPass").toString();
			// 2- validar información obtenida JSP
			if (correoUsuario.trim().length() != 0) {
				if (claveUsuario.trim().length() != 0) {
					alles = usuarioDAO.getAll();
					for (Usuario usuario : alles) {
						if (usuario.getMail().compareTo(correoUsuario) == 0) {
							if (usuario.getClaveUsuario().compareTo(claveUsuario) == 0) {
								usr = usuario;
								existe = true;
								break;
							}
						}
					}
				}
			}
			// 3- verificar resultado

			// SE ALMACENA LA VARIABLE SESSION
			request.getSession().setAttribute(Constantes.sessionUser, usr);
			// 5- Informar estado
			// request.getRequestDispatcher(paginaJsp).forward(request, response);

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			if (!existe) {
				message = "El usuario no esta registrado";
				paginaJsp = "/IniciarSesion.jsp";
			} else if (!usr.isAdmin()) {
				paginaJsp = "/Inicio.jsp";
			} else {
				request.setAttribute("usuariosBajoPuntaje", usuariosBajoPuntaje());
				request.setAttribute("publicacionesBajoPuntaje", publicacionesBajoPuntaje());
				paginaJsp = "/InicioAdmin.jsp";
			}

			request.setAttribute("message", message);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		try {
			// 1- Recuperar valores del formulario JSP
			// 2- Validar información obtenida JSP
			if (request.getSession().getAttribute(Constantes.sessionUser) != null) {
				// request.getSession().setAttribute(Constantes.sessionUser, null);
				request.getSession().removeAttribute(Constantes.sessionUser);
				request.getSession().invalidate();

				throw new ValidacionException("Se cerró su sesión exitosamente. Hasta luego");
			} else {
				throw new ValidacionException("La sesión no fue iniciada. No se pudo finalizar");
			}

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);
			paginaJsp = "/IniciarSesion.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// Esto es para cargar los datos a mostrar en la vista de admin
	public ArrayList<Usuario> usuariosBajoPuntaje() {
		ArrayList<Usuario> todos = usuarioDAO.getAll();
		ArrayList<Usuario> usuariosBajoPuntaje = new ArrayList<Usuario>();
		for (Usuario u : todos) {
			if (u.getPuntaje() < 2) {
				usuariosBajoPuntaje.add(u);
			}
		}
		return usuariosBajoPuntaje;
	}

	public ArrayList<Publicacion> publicacionesBajoPuntaje() {
		ArrayList<Publicacion> todos = publicacionDAO.getAll();
		ArrayList<Publicacion> publicacionesBajoPuntaje = new ArrayList<Publicacion>();
		for (Publicacion u : todos) {
			if (u.getPuntaje() < 2) {
				publicacionesBajoPuntaje.add(u);
			}
		}

		return publicacionesBajoPuntaje;
	}

}
