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
import exceptions.ServidorException;
import exceptions.ValidacionException;
import extra.Constantes;
import extra.InfoMessage;
import extra.LOG;
import extra.Utilitario;
import extra.ORSesion;
import modelo.Publicacion;
import modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
	private final Usuarios usuarioDAO = new Usuarios();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			case "admListaUsuarios":
				cargarAdmListaUsuarios(request, response);
				break;
			case "admGestionarVerificacionUsuarios":
				admGestionarVerificacionUsuarios(request, response);
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String action = request.getParameter("buscarAction");
		String accionPOST = request.getParameter(Constantes.accionPOST);
		LOG.info(String.format("%s POST: %s", Constantes.logJSPAccion, accionPOST));
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
			case "cambiarClaveUsuario":
				cambiarClaveUsuario(request, response);
				break;
			}
		}
	}

	// ********* Usuario: altaUsuario ************* //
	private void altaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario objUsuario = new Usuario();
		String message = null;
		boolean registroUsuarioEsExitoso = false;
		try {
			// 1- Obtiene valores del JSP ya validados
			objUsuario = getUsuarioFromJSPInputs(request, objUsuario);
			// 2- Validar con la DB
			usuarioDAO.validarCamposUnicos(objUsuario);
			// 3- verificar correcto almacenamiento en DB
			if (!usuarioDAO.insert(objUsuario))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");
			// 4- EXITO
			registroUsuarioEsExitoso = true;
			message = Constantes.REGISTROEXITOSO;
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado / Redirigir
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

	private Usuario getUsuarioFromJSPInputs(HttpServletRequest request, Usuario objUsuario) throws ValidacionException {
		// 0- Validar parámetros del JSP
		String[] listaNombreParametrosUsuario = { "mail", "claveUno", "claveDos", "nombre", "apellido", "fechaNac",
				"rdbSexo", "dni" };
		String[] listaNombreCampos = { "mail", "claveUno", "claveDos", "nombre", "apellido", "fecha Nacimiento", "Sexo",
				"dni" };

		Utilitario.validarParametrosObligatoriosDeUnJSP(request, listaNombreParametrosUsuario, listaNombreCampos);
		// 1- recuperar valores del formulario JSP
		String mail = request.getParameter("mail").toString();
		String claveUno = request.getParameter("claveUno").toString();
		String claveDos = request.getParameter("claveDos").toString();
		String nombre = request.getParameter("nombre").toString();
		String apellido = request.getParameter("apellido").toString();
		String fechaNac = request.getParameter("fechaNac");
		boolean sexo = Boolean.valueOf(request.getParameter("rdbSexo"));
		String dni = request.getParameter("dni").toString();
		String nroTelefono = null;
		if (request.getParameter("nroTelefono") != null)
			nroTelefono = request.getParameter("nroTelefono").toString();
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
		objUsuario.setSexo(sexo);
		objUsuario.setNroTelefono(nroTelefono);
		// ByDefault
		objUsuario.setRutaFotoPerfil(Constantes.RUTAuserNoPhoto);
		objUsuario.setAdmin(false);
		objUsuario.setPuntaje(0);
		objUsuario.setHabilitado(true);
		String currentDateString = Utilitario.getCurrentDateAndHoursString();
		objUsuario.setFechaAlta(currentDateString);
		objUsuario.setFechaUltConexion(currentDateString);
		objUsuario.setFechaUltModificado(currentDateString);
		objUsuario.setVerificado(false);
		return objUsuario;
	}

	// ********* Usuario: verInfoUsuario ************* //
	private void verInfoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario objUsuario = null;
		String message = null;

		try {
			// 1- Recuperar valores del formulario JSP
			objUsuario = ORSesion.getUsuarioBySession(request);
			// 2- Validar información obtenida JSP
			if (objUsuario == null) {
				message = "ERROR: No se pudo recuperar la variable Session: " + Constantes.sessionUser;
				LOG.info(message);
				throw new ValidacionException(message);
			}
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

	// ********* Usuario: loginUsuario ************* //
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
					paginaJsp = "/InicioAdmin.jsp";
				}
			}

			request.setAttribute("message", message);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	// ********* Usuario: cerrarSesion ************* //
	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		try {
			if (ORSesion.sesionActiva(request)) {
				// Usuario objUsuario = ORSesion.getUsuarioBySesion(request);
				// objUsuario.setFechaUltConexion(Utilitario.getCurrentDateAndHoursString());
				// usuarioDAO.updateFechaUltConexion(objUsuario);
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

	// ********* Usuario: cargarAdmListaUsuarios ************* //
	private void cargarAdmListaUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;

		try {
			Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(request);
			if (!objUsuarioLogueado.isAdmin())
				throw new ValidacionException("Usted no tiene permisos para realizar esta accción");
			// String fechaUltConexion = objUsuarioLogueado.getFechaUltConexion();
			// ArrayList<Usuario> listaUsuarios = usuarioDAO
			// .getListaNuevosUsuarios(objUsuarioLogueado.getFechaUltConexion());//
			// usuarioDAO.getListaUsuarios_SortByFechaAlta();

			ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaUsuarios = usuarioDAO.getAll();
			// ArrayList<Usuario> listaUsuariosNuevos = new ArrayList<Usuario>();
			// usuarioDAO.getAll().forEach(item -> {
			// if (item.getFechaAlta().compareTo(fechaUltConexion) > 0)
			// listaUsuariosNuevos.add(item);
			// else
			// listaUsuarios.add(item);
			// });

			request.setAttribute("listaUsuarios", listaUsuarios);
			// request.setAttribute("listaUsuariosNuevos", listaUsuariosNuevos);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			request.setAttribute("message", message);
			paginaJsp = "/admListaUsuarios.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	private void admGestionarVerificacionUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 0- Verificar que sea un usuario administrador
			Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(request);
			if (!objUsuarioLogueado.isAdmin())
				throw new ValidacionException("Usted no tiene permisos para realizar esta accción");
			// 1- recuperar valores del request y los DAOs
			if (request.getParameter("verificado") == null) {
				throw new ServidorException("El parámetro 'verificado' es null");
			}
			boolean verificadoPorAdmin = Boolean.valueOf(request.getParameter("verificado").toString());
			if (request.getParameter("idUsuario") == null) {
				throw new ServidorException("El ID del usuario es null");
			}
			// 2- Validar existencia del usuario
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			if (usuarioDAO.getObjectById(idUsuario) == null)
				throw new ServidorException("No se encontraron registros del usuario con ID " + idUsuario);
			// 3- Actualizar en DB
			if (verificadoPorAdmin) {
				if (!usuarioDAO.updateVerificado(idUsuario, true))
					throw new ServidorException(
							"SQL error al ejecutar el 'updateVerificado' para el usuario con ID " + idUsuario);

				message = "Se marcó como verificado al usuario con ID " + idUsuario;
			} else {
				if (!usuarioDAO.updateVerificado(idUsuario, false))
					throw new ServidorException(
							"SQL error al ejecutar el 'updateVerificado' para el usuario con ID " + idUsuario);

				message = "Se marcó como NO verificado al usuario con ID " + idUsuario;

			}

			if (request.getAttribute("objInfoMessage") == null) {

				objInfoMessage = new InfoMessage(true, message);
			}
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			paginaJsp = "UsuarioServlet?accionGET=admListaUsuarios";
			request.getSession().setAttribute("objInfoMessage", objInfoMessage);
			response.sendRedirect(paginaJsp);
		}
	}

	private void cambiarClaveUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 0- Verificar que esté logueado
			Usuario usuario = ORSesion.getUsuarioBySession(request);
			if(usuario == null)
				throw new ServidorException("No se pudo encontrar la sesion del usuario.");
			int idUsuarioLogueado = usuario.getIdUsuario();
			
			// 1- recuperar valores del request y los DAOs
			if(request.getParameter("claveActual") == null)
				throw new ServidorException("El parametro 'Contraseña Actual' no pudo llegar al servidor.");
			if(request.getParameter("claveActual2") == null)
				throw new ServidorException("El parametro 'Vuelva a ingresar la contraseña actual' no pudo llegar al servidor.");
			if (request.getParameter("claveUno") == null) {
				throw new ServidorException("El parametro 'Nueva Contraseña' no pudo llegar al servidor.");
			}
			if (request.getParameter("claveDos") == null) {
				throw new ServidorException("El parametro 'Repita Contraseña' no pudo llegar al servidor.");
			}
			String claveUno = request.getParameter("claveUno").toString();
			String claveDos = request.getParameter("claveDos").toString();
			String actualUno = request.getParameter("claveActual").toString();
			String actualDos = request.getParameter("claveActual2").toString();
			
			// 2- Validar existencia del usuario
			if (!claveUno.equals(claveDos)) {
				throw new ValidacionException("Las claves NUEVAS ingresadas no coinciden.");
			}
			if(!actualUno.equals(actualDos))
				throw new ValidacionException("Las claves ACTUALES ingresadas no coinciden.");
			
			if(!actualUno.equals(usuario.getClave()) || !actualDos.equals(usuario.getClave()))
				throw new ValidacionException("La clave ACTUAL no es correcta.");
			
			if(actualUno.equals(claveUno) || actualUno.equals(claveDos) || actualDos.equals(claveUno) || actualDos.equals(claveDos) || claveUno.equals(usuario.getClave()) || claveDos.equals(usuario.getClave()))
				throw new ValidacionException("La clave NUEVA debe ser distinta a la ACTUAL.");
			
			// 3- Actualizar en DB
			if (!usuarioDAO.updateClave(idUsuarioLogueado, claveUno))
				throw new ServidorException("SQL error al actualizar la clave del usuario con ID " + idUsuarioLogueado);

			message = "Se cambió la clave con éxito";

			if (request.getAttribute("objInfoMessage") == null) {

				objInfoMessage = new InfoMessage(true, message);
			}
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			paginaJsp = "UsuarioServlet?accionGET=MiPerfil";
			request.getSession().setAttribute("objInfoMessage", objInfoMessage);
			response.sendRedirect(paginaJsp);
		}
	}

}
