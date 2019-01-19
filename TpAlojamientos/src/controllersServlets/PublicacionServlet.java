package controllersServlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controladoresDAO.Partidos;
import controladoresDAO.Comentarios;
import controladoresDAO.Localidades;
import controladoresDAO.Publicaciones;
import controladoresDAO.Usuarios;
import controladoresDAO.Imagenes;
import exceptions.ServidorException;
import exceptions.CargaViewException;
import exceptions.LectorDatosException;
import extra.Constantes;
import extra.LOG;
import modelo.Comentario;
import modelo.Localidad;
import modelo.Partido;
import modelo.Publicacion;
import modelo.Usuario;
import views.PublicacionView;
import views.PaginacionView;

/**
 * Servlet implementation class PublicacionServlet
 */
@WebServlet(urlPatterns = { "/PublicacionServlet" })
public class PublicacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
	private final Publicaciones publicacionDAO = new Publicaciones();
	private final Partidos partidosDAO = new Partidos();
	private final Localidades localidadDAO = new Localidades();
	private final Comentarios comentarioDAO = new Comentarios();
	private final Usuarios usuarioDAO = new Usuarios();
	private final Imagenes imagenDAO = new Imagenes();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicacionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String accionGET = request.getParameter(Constantes.accionGET);
			if (accionGET == null) {
				throw new ServidorException("NULL Param: " + Constantes.accionGET + " en PublicacionServlet");
			}
			LOG.info(String.format("%s GET: %s", Constantes.logJSPAccion, accionGET));
			switch (accionGET) {
			case "VerPublicacion":
				verPublicacion(request, response);
				break;
			case "VerPublicaciones":
				verPublicaciones(request, response);
				break;
			case "Nuevo":
				cargarComponentesAltaPublicacion(request, response);
				// altaPublicacion(request, response);
				break;
			case "VerComentarios":
				verComentariosPublicacion(request, response);
				break;
			case "Buscar":
				buscarPublicaciones(request, response);
				break;
			}
		} catch (ServidorException e) {
			e.printStackTrace();
		} catch (CargaViewException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String accionPOST = request.getParameter(Constantes.accionPOST);
			if (accionPOST == null) {
				throw new ServidorException("NULL Param: " + Constantes.accionPOST + " en PublicacionServlet");
			}
			LOG.info(String.format("%s POST: %s", Constantes.logJSPAccion, accionPOST));
			switch (accionPOST) {
			case "getLocalidades":
				// nombre provisorio
				cargarLocalidadesAjax(request, response);
				break;
			case "cmbPartidoSubmit":
				cargarComponentesAltaPublicacion(request, response);
				break;
			case "create":
				cargarComponentesAltaPublicacion(request, response);
				// altaPublicacion(request, response);
				break;
			case "VerComentarios":
				verComentariosPublicacion(request, response);
				break;
			case "read":
				break;
			case "update":
				break;
			case "delete":
				break;
			}
		} catch (ServidorException e) {
			e.printStackTrace();
		}
	}

	private void cargarComponentesAltaPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- cargar los componentes con los valores de la DB
		// 1- recuperar valores del formulario JSP
		// 2- validar informacion obtenida JSP
		// 2.2 Validar con la DB
		// 3- guardar informacion validada
		// 4- verificar correcto almacenamiento en DB
		// 5- Informar estado en interfaz (jsp)
		String message = null;

		try {
			ArrayList<Partido> listaPartidos = partidosDAO.getAll();
			ArrayList<Localidad> listaLocalidades = null;

			if (request.getParameter("cmbPartido") != null) {
				int idPartido = Integer.parseInt(request.getParameter("cmbPartido"));
				listaLocalidades = localidadDAO.getAllByIdPartido(idPartido);
			}
			request.setAttribute("listaPartidos", listaPartidos);
			request.setAttribute("listaLocalidades", listaLocalidades);
			message = "El cmbPartido fue cargado";
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionAlta.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	private void altaPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- cargar los componentes con los valores de la DB
		// 1- recuperar valores del formulario JSP
		// 2- validar informacion obtenida JSP
		// 2.2 Validar con la DB
		// 3- guardar informacion validada
		// 4- verificar correcto almacenamiento en DB
		// 5- Informar estado en interfaz (jsp)
		String message = null;

		try {

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			paginaJsp = "/PublicacionAlta.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	private void verComentariosPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- Declaración de variables
		String message = null;
		ArrayList<Comentario> listaComentarios = null;
		float publicacionPuntaje = 0;
		try {
			// 1- recuperar valores del request y los DAOs
			int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			publicacionPuntaje = publicacionDAO.getObjectByID(idPublicacion).getPuntaje();
			listaComentarios = comentarioDAO.getAllByIdPublicacion(idPublicacion);
			// 2- validar información obtenida
			if (listaComentarios == null) {
				throw new LectorDatosException(
						"ERROR: No se encontraron comentarios para el idPublicacion" + idPublicacion);
			} else {
				if (listaComentarios.isEmpty())
					message = "La lista de comentarios está vacía";
				else
					message = "Se filtró la lista de comentarios con éxito";
			}
			// 3- guardar información en request para su posterior muestra/exposición en JSP
			request.setAttribute("listaComentarios", listaComentarios);
			request.setAttribute("publicacionPuntaje", publicacionPuntaje);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 4- Informar estado/resultados en interfaz (JSP)
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionComentarios.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

	/**********************************************************************/
	/******************************* AJAX *********************************/
	/**********************************************************************/

	private void cargarLocalidadesAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// DEBE REALIZARSE EN ALGUNA FUNCION QUE NO HAGA REDIRIGIR LA PAGINA A OTRA.

		if (request.getParameter("idPartido") != null) {

			Map<String, Object> resultMap = new HashMap<String, Object>();
			ArrayList<Localidad> localidades = new ArrayList<Localidad>();

			int idPartido = Integer.parseInt(request.getParameter("idPartido"));
			localidades = localidadDAO.getAllByIdPartido(idPartido);

			if (localidades != null) {
				resultMap.put("localidades", localidades);
			} else {
				resultMap.put("error", "no se pudo realizar la carga de localidaes");
			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(new Gson().toJson(resultMap)); // <----- AJAX RESPONDE SIN REDIRIGIR
		}

	}

	private void verPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CargaViewException {
		// 0- Declaración de variables
		String message = null;
		int cantidadComentarios = 0;
		int idPublicacion = 0;
		PublicacionView vistaPublicacion = new PublicacionView();
		try {
			// 1- recuperar valores del request y los DAOs
			if (request.getParameter("idPublicacion") != null) {
				idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			}
			// 1.1 DAO recuperar publicacion
			Publicacion objPublicacion = publicacionDAO.getObjectByID(idPublicacion);
			if (objPublicacion == null)
				throw new LectorDatosException("ERROR: No se encontró la Publicación con ID" + idPublicacion);
			// 1.2 DAO recuperar usuario de la publicacion
			Usuario objUsuario = usuarioDAO.getUsuarioById(objPublicacion.getIdUsuario());
			if (objUsuario == null)
				throw new LectorDatosException(
						"ERROR: No se encontró un usuario con ID" + objPublicacion.getIdUsuario());
			// 1.3 DAO recuperar lista de imagenes de la publicacion
			vistaPublicacion.setImagenes(imagenDAO.getAllByIdPublicacion(idPublicacion));
			// 1.4 DAO recuperar cantidad de Comentarios de la publicacion
			cantidadComentarios = comentarioDAO.getAllByIdPublicacion(idPublicacion).size();
			// 1.5 DAO recuperar ubicación TODO: revisar
			Localidad objLocalidad = localidadDAO.getLocalidadById(objPublicacion.getIdLocalidad());
			objLocalidad.setNombrePartido(localidadDAO.getNombrePartido(objLocalidad.getIdPartido()));

			vistaPublicacion.setPublicacion(objPublicacion);
			vistaPublicacion.setUsuario(objUsuario);
			vistaPublicacion.setCantComentarios(cantidadComentarios);
			// TODO: Revisar. Ya no sería necesario si se usa la clase view que ya contiene
			// a otras clases
			// request.setAttribute("publicacion", objPublicacion);
			// request.setAttribute("usuarioPublicacion", objUsuario);
			request.setAttribute("vistaPublicacion", vistaPublicacion);
			request.setAttribute("objLocalidad", objLocalidad);

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 4- Informar estado/resultados en interfaz (JSP)
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionView.jsp";
			request.getRequestDispatcher(paginaJsp).forward(request, response);
		}
	}

	private void verPublicaciones(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, CargaViewException {
		int cantidadComentarios = 0;
		PaginacionView pagination = PaginacionView.crearPaginacion(request.getParameter("Pagina"),
				publicacionDAO.getCount());

		ArrayList<Publicacion> listaPublicacion = publicacionDAO.getLimit(pagination.getPaginaActual(),
				pagination.getCantidadElementos());
		ArrayList<PublicacionView> listaPublicacionView = new ArrayList<PublicacionView>();

		if (listaPublicacion != null) {
			for (Publicacion p : listaPublicacion) {
				PublicacionView vistaPublicacion = new PublicacionView();

				// Primero buscamos la publicacion
				int idPublicacion = p.getIdPublicacion();
				Publicacion objPublicacion = publicacionDAO.getObjectByID(idPublicacion);
				if (objPublicacion != null) {
					request.setAttribute("publicacion", objPublicacion);

					vistaPublicacion.setPublicacion(objPublicacion);
					// Buscamos los datos del usuario de la publicacion
					Usuario objUsuario = usuarioDAO.getUsuarioById(objPublicacion.getIdUsuario());
					if (objUsuario != null) {
						request.setAttribute("usuarioPublicacion", objUsuario);
						vistaPublicacion.setUsuario(objUsuario);
					} // Validaciones del else?

					// Ahora buscamos las rutas de las imagenes de la publicacion
					// vistaPublicacion.cargarImagenes(imagenDAO.getAll());
					// vistaPublicacion.cargarImagenes(imagenDAO.getAllByIdPublicacion(idPublicacion));

					// Despues buscamos la cantidad de comentarios
					cantidadComentarios = 0;
					cantidadComentarios = comentarioDAO.getAllByIdPublicacion(idPublicacion).size();
					vistaPublicacion.setCantComentarios(cantidadComentarios);
					listaPublicacionView.add(vistaPublicacion);
				} // Validaciones del else?
			}
		} // Validaciones del else?
		request.setAttribute("publicaciones", listaPublicacionView);
		request.setAttribute("paginacion", pagination);

		paginaJsp = "/Publicaciones.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}

	private void buscarPublicaciones(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Modulo de busqueda
	}

}
