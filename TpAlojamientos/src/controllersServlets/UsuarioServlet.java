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
import extra.ORSesion;
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

		String accionGET = request.getParameter(Constantes.accionGET);
		LOG.info(String.format("%s GET Banner: %s", Constantes.logJSPAccion, accionGET));
		if (accionGET != null) {
			switch (accionGET) {
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
		// String action = request.getParameter("buscarAction");
		String accionPOST = request.getParameter(Constantes.accionPOST);
		LOG.info(String.format("%s POST Banner: %s", Constantes.logJSPAccion, accionPOST));
		if (accionPOST != null) {
			switch (accionPOST) {
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
		Usuario objUsuario = new Usuario();
		String message = null;
		boolean registroUsuarioEsExitoso = false;
		try {
			// 1- recuperar valores del formulario JSP
			String mail = request.getParameter("mail").toString();
			String claveUno = request.getParameter("claveUno").toString();
			String claveDos = request.getParameter("claveDos").toString();
			String nombre = request.getParameter("nombre").toString();
			String apellido = request.getParameter("apellido").toString();
			String fechaNac = request.getParameter("fechaNac");
			boolean sexo = Boolean.valueOf(request.getParameter("rdbSexo"));
			String dni = request.getParameter("dni").toString();
			// String telefono = request.getParameter("telefono").toString();

			// 2- validar informacion obtenida JSP
			if (!claveUno.equals(claveDos)) {
				throw new ValidacionException("Las claves son diferentes. Por favor revisar que sean identicas");
			}
			if (!Utilitario.esMayorDeEdad(fechaNac)) {
				throw new ValidacionException("El usuario debe ser mayor de edad");
			}

			// 3- guardar informacion validada
			objUsuario.setIdUsuario(usuarioDAO.getCount() + 1);
			objUsuario.setNombre(nombre);
			objUsuario.setApellido(apellido);
			objUsuario.setDni(dni);
			objUsuario.setMail(mail);
			objUsuario.setFechaNac(Utilitario.textoAFechaSQL(fechaNac));
			objUsuario.setUsuario("newUser");
			objUsuario.setClave(claveUno);
			objUsuario.setSexo(sexo);// obj.setTelefono(telefono);
			// ByDefault
			objUsuario.setRutaFotoPerfil(Constantes.RUTAuserNoPhoto);
			objUsuario.setAdmin(false);
			objUsuario.setPuntaje(0);
			objUsuario.setHabilitado(true);
			objUsuario.setFechaAlta(Utilitario.getCurrentDateAndHoursString());
			objUsuario.setFechaUltConexion(Utilitario.getCurrentDateAndHoursString());
			objUsuario.setVerificado(false);
			// 2.2 Validar con la DB
			usuarioDAO.validarCamposUnicos(objUsuario);
			// 4- verificar correcto almacenamiento en DB
			if (!usuarioDAO.insert(objUsuario))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");
			// EXITO
			registroUsuarioEsExitoso = true;
			message = Constantes.REGISTROEXITOSO;
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);

			// response.getWriter().append("<script>alert(" + message + ")</script>");
			if (registroUsuarioEsExitoso) {
				ORSesion.nuevaSesion(request, objUsuario);
				paginaJsp = "/Inicio.jsp";
			} else
				paginaJsp = "/UsuarioAlta.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// ********* VER DATOS
	private void verInfoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario objUsuario = null;
		String message = null;

		try {
			// 1- Recuperar valores del formulario JSP
			// user = (Usuario) request.getSession().getAttribute(Constantes.sessionUser);
			objUsuario = ORSesion.getUsuarioBySesion(request);
			// 2- Validar información obtenida JSP
			if (objUsuario == null) {
				message = "ERROR: No se pudo recuperar la variable Session: " + Constantes.sessionUser;
				LOG.info(message);
				throw new ValidacionException(message);
			}
			// 3- Recuperar info de la DB
			if (usuarioDAO.get(objUsuario) == null)
				throw new ValidacionException(
						"SQL: Ocurrió un error al recuperar usuario con id" + objUsuario.getIdUsuario());
			// 4- Devolver información recuperada a la jsp
			request.setAttribute("objUsuario", objUsuario);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);

			// //request.setAttribute("rutaDefaultFoto", Constantes.RUTAuserNoPhoto);
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
		Usuario objUsuario = new Usuario();
		try {
			// 1- recuperar valores del formulario JSP
			String correoUsuario = request.getParameter("txtUser").toString();
			String claveUsuario = request.getParameter("txtPass").toString();
			// 2- validar informacion obtenida JSP
			objUsuario = usuarioDAO.getUsuarioByLogin(correoUsuario, claveUsuario);
			// 3- verificar resultado
			if (objUsuario == null)
				throw new ValidacionException("El usuario no está registrado");
			// 4- Se guarda una variable SESSION
			ORSesion.nuevaSesion(request, objUsuario);
			// 5- Se actualiza la FechaUltConexion con la actual.
			// La anterior es objUsuario.getAnteriorFechaUltConexion
			objUsuario.setFechaUltConexion(Utilitario.getCurrentDateAndHoursString());
			usuarioDAO.updateFechaUltConexion(objUsuario);
			// 6- Informar estado
			message = "Login exitoso";
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			if (objUsuario == null) {
				paginaJsp = "/IniciarSesion.jsp";
			} else {
				if (!objUsuario.isAdmin())
					paginaJsp = "/Inicio.jsp";
				else {
					// request.setAttribute("usuariosBajoPuntaje", usuariosBajoPuntaje());
					// request.setAttribute("publicacionesBajoPuntaje", publicacionesBajoPuntaje());
					paginaJsp = "/InicioAdmin.jsp";
				}
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
			if (ORSesion.sesionActiva(request)) {
				Usuario objUsuario = ORSesion.getUsuarioBySesion(request);
				objUsuario.setFechaUltConexion(Utilitario.getCurrentDateAndHoursString());
				usuarioDAO.updateFechaUltConexion(objUsuario);
				ORSesion.cerrarSesion(request);
				throw new ValidacionException("Su sesión fue cerrada exitosamente. Hasta luego");
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
