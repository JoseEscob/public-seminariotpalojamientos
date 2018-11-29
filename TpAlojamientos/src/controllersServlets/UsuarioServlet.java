package controllersServlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private Usuarios usuarioDAO = new Usuarios();
	private Publicaciones publicacionDAO = new Publicaciones();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("buscarAction");
		LOG.info("JSP - Acción: " + action);
		if (action != null) {
			switch (action) {
			case "login":
				LoginUsuario(request, response);
				break;
			case "nuevo":
				AltaUsuario(request, response);
				break;
			case "editar":
				// buscarPorNombre(request, response);
				break;
			case "ver":
				VerInfoUsuario(request, response);
				break;
			}
		}
	}

	private void AltaUsuario(HttpServletRequest request, HttpServletResponse response)
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

			paginaJsp = "/UsuarioAlta.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// ********* VER DATOS
	private void VerInfoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario obj = new Usuario();
		String message = null;

		try {
			// 1- recuperar valores del formulario JSP
			HttpSession session = request.getSession();
			String idUsuario = null;// getParameter("txtHiddenIdUsuario")

			// 2- validar información obtenida JSP
			if (request.getParameter("idUsuario") != null) {
				idUsuario = request.getParameter("idUsuario").toString();
			}
			session.setAttribute("sessionJSP", idUsuario);// session.getAttribute("sessionJSP");

			// 3- guardar información validada
			obj.setIdUsuario(Integer.parseInt(idUsuario));
			// 4- verificar correcto almacenamiento en DB
			if (usuarioDAO.get(obj) == null)
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");

			request.setAttribute("obj", obj);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);

			paginaJsp = "/UsuarioAlta.jsp";
			// response.sendRedirect(request.getContextPath()+ paginaJsp);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// ********* LOGIN

	private void LoginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- declaracion de variables locales
		String message = null;

		Usuario usr = new Usuario();
		ArrayList<Usuario> alles = new ArrayList<Usuario>();
		boolean existe = false;
		try {
			// 1- recuperar valores del formulario JSP
			String nombreUsuario = request.getParameter("txtUser").toString();
			String claveUsuario = request.getParameter("txtPass").toString();
			// 2- validar información obtenida JSP
			if (nombreUsuario.trim().length() != 0) {
				if (claveUsuario.trim().length() != 0) {
					alles = usuarioDAO.getAll();
					for (Usuario usuario : alles) {
						if (usuario.getUsuario().compareTo(nombreUsuario) == 0) {
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
			request.getSession().setAttribute("usuario", usr);
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
			}else {
				request.setAttribute("usuariosBajoPuntaje", usuariosBajoPuntaje());
				request.setAttribute("publicacionesBajoPuntaje", publicacionesBajoPuntaje());
				paginaJsp = "/InicioAdmin.jsp";
			}

			request.setAttribute("message", message);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}
	
	// Esto es para cargar los datos a mostrar en la vista de admin
	public ArrayList<Usuario> usuariosBajoPuntaje() {
		ArrayList<Usuario> todos = usuarioDAO.getAll();
		ArrayList<Usuario> usuariosBajoPuntaje = new ArrayList<Usuario>();
		for(Usuario u : todos) {
			if(u.getPuntaje() < 2) {
				usuariosBajoPuntaje.add(u);
			}
		}
		return usuariosBajoPuntaje;
	}
	
	public ArrayList<Publicacion> publicacionesBajoPuntaje() {
		ArrayList<Publicacion> todos = publicacionDAO.getAll();
		ArrayList<Publicacion> publicacionesBajoPuntaje = new ArrayList<Publicacion>();
		for(Publicacion u : todos) {
			if(u.getPuntaje() < 2) {
				publicacionesBajoPuntaje.add(u);
			}
		}
		
		return publicacionesBajoPuntaje;
	}

}
