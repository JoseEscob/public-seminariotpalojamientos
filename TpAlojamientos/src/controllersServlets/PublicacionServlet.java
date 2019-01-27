package controllersServlets;

import java.io.IOException;
import java.text.ParseException;
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
import controladoresDAO.Favoritos;
import controladoresDAO.Localidades;
import controladoresDAO.Publicaciones;
import controladoresDAO.Servicios;
import controladoresDAO.Usuarios;
import controladoresDAO.Imagenes;
import controladoresDAO.TiposAlojamientos;
import controladoresDAO.TiposServicios;
import exceptions.ServidorException;
import exceptions.ValidacionException;
import exceptions.CargaViewException;
import exceptions.LectorDatosException;
import extra.Constantes;
import extra.LOG;
import extra.ORSesion;
import extra.Utilitario;
import modelo.Comentario;
import modelo.Favorito;
import modelo.Localidad;
import modelo.Partido;
import modelo.Publicacion;
import modelo.Servicio;
import modelo.Usuario;
import modelo.TipoAlojamiento;
import modelo.TipoServicio;
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
	private final Favoritos favoritosDAO = new Favoritos();
	private final TiposAlojamientos tipoAlojamientoDAO = new TiposAlojamientos();
	private final Servicios serviciosDAO = new Servicios();

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
			case "GestionarFavoritos":
				gestionarFavoritos(request, response);
				break;
			case "nuevaPublicacion":
				nuevaPublicacion(request, response);
				break;
			case "read":
				break;
			case "update":
				break;
			case "delete":
				break;
			}
		} catch (Exception e) {
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
			ArrayList<TipoAlojamiento> listaTiposAlojamientos = tipoAlojamientoDAO.getAll();
			ArrayList<TipoServicio> listaTipoServicios = new TiposServicios().getAll();

			if (request.getParameter("cmbPartido") != null) {
				int idPartido = Integer.parseInt(request.getParameter("cmbPartido"));
				listaLocalidades = localidadDAO.getAllByIdPartido(idPartido);
			}
			request.setAttribute("listaTiposAlojamientos", listaTiposAlojamientos);
			request.setAttribute("listaPartidos", listaPartidos);
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaTipoServicios", listaTipoServicios);
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

	/**
	 * Guarda o modifica el estado de una publicacion en favoritos
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void gestionarFavoritos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;

		try {
			int idPublicacion;
			boolean agregaAFavoritos = true;
			// 1- recuperar valores del request y los DAOs
			// 1.1 request: idPublicacion
			if (request.getParameter("idPublicacion") == null) {
				throw new ServidorException("ERROR NULL Parameters: idPublicacion");
			}
			idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			// 1.2 request: agregaAFavoritos - accion elegida por el usuario
			if (request.getParameter("agregaAFavoritos") == null) {
				throw new ServidorException("ERROR NULL Parameters: agregaAFavoritos");
			}
			agregaAFavoritos = Boolean.parseBoolean(request.getParameter("agregaAFavoritos"));

			// 1.3 variable sesión
			int idUsuarioLogueado = ORSesion.getUsuarioBySession(request).getIdUsuario();
			// 1.4 request: vistaPublicacion
			// TODO: Acá se puede laburar con applicationScope en vez de usar
			// obtenerPublicacionView
			/*
			 * if (request.getParameter("vistaPublicacion") == null) { throw new
			 * ServidorException("ERROR NULL Parameters: vistaPublicacion"); }
			 * PublicacionView vistaPublicacion = (PublicacionView)
			 * request.getServletContext().getAttribute("vistaPublicacion");
			 */

			PublicacionView vistaPublicacion = obtenerPublicacionView(idUsuarioLogueado, idPublicacion);

			// 2- Gestión de favoritos
			Favorito objFavorito = vistaPublicacion.getObjFavorito();
			if (objFavorito == null) {
				// 2.1 Crea nuevo registro en la DB
				favoritosDAO.guardarNuevoFavorito(idUsuarioLogueado, idPublicacion);
			} else {
				// 2.1 Modifica el estado del registro en la DB
				if (agregaAFavoritos)
					favoritosDAO.habilitarFavoritoExistente(objFavorito);
				else
					favoritosDAO.deshabilitarFavoritoExistente(objFavorito);
			}
			// 2.2 Actualiza el objFavorito de la vista con una consulta a la DB
			objFavorito = favoritosDAO.getObjFavoritoByParams(idUsuarioLogueado, idPublicacion);
			// 3- Guardar en objeto de la clase
			vistaPublicacion.setObjFavorito(objFavorito);
			if (agregaAFavoritos)
				message = String.format("Se agregó la publicación %d a su lista de favoritos", idPublicacion);
			else
				message = String.format("Se eliminó la publicación %d de su lista de favoritos", idPublicacion);
			request.setAttribute("vistaPublicacion", vistaPublicacion);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionView.jsp";
			request.getRequestDispatcher(paginaJsp).forward(request, response);
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

	/**********************************************************************/

	/*********************** ABML Publicaciones ***************************/
	private PublicacionView obtenerPublicacionView(int idUsuarioLogueado, int idPublicacion)
			throws LectorDatosException {
		PublicacionView vistaPublicacion = new PublicacionView();
		int cantidadComentarios = 0;
		// 1.1 DAO recuperar publicacion
		Publicacion objPublicacion = publicacionDAO.getObjectByID(idPublicacion);
		if (objPublicacion == null)
			throw new LectorDatosException("ERROR: No se encontró la Publicación con ID" + idPublicacion);
		// 1.2 DAO recuperar usuario de la publicacion
		int idUsuarioPublicador = objPublicacion.getIdUsuario();
		Usuario objUsuario = usuarioDAO.getUsuarioById(idUsuarioPublicador);
		if (objUsuario == null)
			throw new LectorDatosException("ERROR: No se encontró un usuario con ID" + idUsuarioPublicador);
		// 1.3 DAO recuperar lista de imagenes de la publicacion
		vistaPublicacion.setImagenes(imagenDAO.getAllByIdPublicacion(idPublicacion));
		// 1.4 DAO recuperar cantidad de Comentarios de la publicacion
		cantidadComentarios = comentarioDAO.getAllByIdPublicacion(idPublicacion).size();
		// 1.5 DAO recuperar ubicación TODO: revisar
		Localidad objLocalidad = localidadDAO.getLocalidadById(objPublicacion.getIdLocalidad());
		objLocalidad.setNombrePartido(localidadDAO.getNombrePartido(objLocalidad.getIdPartido()));
		// 1.6 DAO recuperar datos de clase Favoritos

		// Favorito objFavorito = favoritosDAO.get(idUsuarioLogueado, idPublicacion);
		Favorito objFavorito = favoritosDAO.getObjFavoritoByParams(idUsuarioLogueado, idPublicacion);
		// 1.7 DAO Recuperar servicios de publicación
		vistaPublicacion.setListaServicios(serviciosDAO.getAllByIdPublicacion(idPublicacion));

		// String carpetaImgPublicacion =
		// imagenDAO.getAllByIdPublicacion(idPublicacion).get(0).getRutaImgPublicacion();

		String carpetaImgPublicacion = Constantes.RUTAFolderFotosPublicacion + objPublicacion.getIdPublicacion() + "/";
		// carpetaImgPublicacion =
		// "C:\\Users\\..\\git\\seminariotpalojamientos\\TpAlojamientos\\WebContent\\imagenes\\publicaciones\\Publicacion_1";
		// ArrayList<String> listaRutaImg =
		// Utilitario.getFilenamesFromFolder(carpetaImgPublicacion);
		ArrayList<String> listaRutaImg = null;

		vistaPublicacion.setListaRutaImg(listaRutaImg);
		vistaPublicacion.setPublicacion(objPublicacion);
		vistaPublicacion.setUsuario(objUsuario);
		vistaPublicacion.setCantComentarios(cantidadComentarios);
		vistaPublicacion.setObjLocalidad(objLocalidad);
		vistaPublicacion.setObjFavorito(objFavorito);
		return vistaPublicacion;
	}

	private void verPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- Declaración de variables
		String message = null;

		int idPublicacion = 0;

		try {
			// 1- recuperar valores del request y los DAOs
			if (request.getParameter("idPublicacion") != null) {
				idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			}
			int idUsuarioLogueado = ORSesion.getUsuarioBySession(request).getIdUsuario();
			PublicacionView vistaPublicacion = obtenerPublicacionView(idUsuarioLogueado, idPublicacion);
			request.setAttribute("vistaPublicacion", vistaPublicacion);
			// request.setAttribute("objLocalidad", objLocalidad);

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

	private void nuevaPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String message = null;
		try {
			if (!ORSesion.sesionActiva(request)) {
				throw new ServidorException("No se encontró iniciada la sesión del usuario");
			}
			// 1- recuperar valores de DAO
			int idPublicacion = publicacionDAO.getCount() + 1;
			// 2- recuperar valores del formulario JSP y validar información obtenida
			Publicacion objPublicacion = new Publicacion();
			objPublicacion = getObjectPublicacionByJSPData(request, idPublicacion);
			LOG.info("Objeto seteado Publicación: " + objPublicacion.toString());
			// 2.2 Validar con la DB
			// 3.1- DB: guardar información validada - publicacionDAO
			if (publicacionDAO.insert(objPublicacion)) {
				String mensaje = "Publicacion cargada con exito.";
				request.setAttribute("objMensaje", mensaje);

			}
			// 3.2- DB: guardar información - serviciosDAO
			String[] chklistServicios = request.getParameterValues("chklistServicios");
			ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
			Servicio objServicio = new Servicio();
			int idServicio = 0;
			for (String chkServicio : chklistServicios) {
				// Servicio objServicio = new Servicio();
				idServicio = Integer.parseInt(chkServicio);
				objServicio.setIdPublicacion(idPublicacion);
				objServicio.setIdServicio(idServicio);
				listaServicios.add(objServicio);
			}
			// 3.2.2- verificar correcto almacenamiento en DB
			int cantArchivosInsertado = 0;
			for (Servicio objServ : listaServicios) {
				if (serviciosDAO.insert(objServ))
					cantArchivosInsertado++;
			}
			LOG.info(String.format("Se guardaron %d de %d servicios ingresados", cantArchivosInsertado,
					listaServicios.size()));
			// 3.3- DB: guardar información - imagenesDAO

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado en interfaz (jsp)
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionAlta.jsp";

			paginaJsp = "/Publicaciones.jsp";
			request.getRequestDispatcher(paginaJsp).forward(request, response);
		}
	}

	private Publicacion getObjectPublicacionByJSPData(HttpServletRequest request, int idPublicacion)
			throws ValidacionException {

		// 0- Validar parámetros del JSP
		String[] listaNombreParametros = { "localidad", "calle", "altura", "codPostal", "cmbTipoAlojamiento",
				"superficieCubierta", "superficieDescubierta", "cantidadPersonas", "cantidadAmbientes",
				"cantidadDormitorios", "cantidadBaños", "chkExpensas", "precioNoche", "aniosAntiguedad", "descripcion",
				"chkPuedeVariarCantPersonas" };
		String[] listaNombreCampos = { "localidad", "calle", "altura", "codPostal", "Tipo Alojamiento",
				"superficieCubierta", "superficieDescubierta", "cantidadPersonas", "cantidadAmbientes",
				"cantidadDormitorios", "cantidadBaños", "chkExpensas", "precioNoche", "aniosAntiguedad", "descripcion",
				"chkPuedeVariarCantPersonas" };

		Utilitario.validarParametrosObligatoriosDeUnJSP(request, listaNombreParametros, listaNombreCampos);

		Publicacion objPublicacion = new Publicacion();
		// int idPartido = Integer.parseInt(request.getParameter("partido"));
		int idLocalidad = Integer.parseInt(request.getParameter("localidad"));
		String calle = request.getParameter("calle");
		int altura = Integer.parseInt(request.getParameter("altura"));
		int codPostal = Integer.parseInt(request.getParameter("codPostal"));
		int idTipoAlojamiento = Integer.parseInt(request.getParameter("cmbTipoAlojamiento"));
		// Si no es depto
		int piso = 0;
		String departamento = "";
		if (idTipoAlojamiento == 3) {
			piso = Integer.parseInt(request.getParameter("piso"));
			departamento = request.getParameter("departamento");
		}

		// int tipoAlojamiento =
		// Integer.parseInt(request.getParameter("tipoAlojamiento"));
		int superficieCubierta = Integer.parseInt(request.getParameter("superficieCubierta"));
		int superficieDescubierta = Integer.parseInt(request.getParameter("superficieDescubierta"));
		int cantidadPersonas = Integer.parseInt(request.getParameter("cantidadPersonas"));
		int cantidadAmbientes = Integer.parseInt(request.getParameter("cantidadAmbientes"));
		int cantidadDormitorios = Integer.parseInt(request.getParameter("cantidadDormitorios"));
		int cantidadBanios = Integer.parseInt(request.getParameter("cantidadBaños"));
		boolean expensas = Boolean.parseBoolean(request.getParameter("chkExpensas"));
		int precioExpensas = 0;
		if (expensas == true)
			precioExpensas = Integer.parseInt(request.getParameter("precioExpensas"));
		int precioNoche = Integer.parseInt(request.getParameter("precioNoche"));
		int aniosAntiguedad = Integer.parseInt(request.getParameter("aniosAntiguedad"));
		String descripcion = request.getParameter("descripcion");

		boolean chkPuedeVariarCantPersonas = Boolean.parseBoolean(request.getParameter("chkPuedeVariarCantPersonas"));

		int idUsuarioLogueado = ORSesion.getUsuarioBySession(request).getIdUsuario();
		objPublicacion.setIdPublicacion(idPublicacion);
		objPublicacion.setIdUsuario(idUsuarioLogueado);
		objPublicacion.setIdTipoAlojamiento(1);
		objPublicacion.setDescripcion(descripcion);
		objPublicacion.setIdLocalidad(idLocalidad);
		objPublicacion.setCodPostal(codPostal);
		objPublicacion.setCoordenadas("null");

		objPublicacion.setCalle(calle);
		objPublicacion.setAltura(altura);
		objPublicacion.setPiso(piso);
		objPublicacion.setDpto(departamento);
		objPublicacion.setSupCubierta(superficieCubierta);
		objPublicacion.setSupDescubierta(superficieDescubierta);
		objPublicacion.setPrecioExpensas(precioExpensas);
		objPublicacion.setPrecioNoche(precioNoche);
		objPublicacion.setChkPuedeVariarCantPersonas(chkPuedeVariarCantPersonas);
		objPublicacion.setCantPersonas(cantidadPersonas);
		objPublicacion.setCantAmbientes(cantidadAmbientes);
		objPublicacion.setCantBanios(cantidadBanios);
		objPublicacion.setCantHabitaciones(cantidadDormitorios);
		objPublicacion.setAniosAntiguedad(aniosAntiguedad);
		java.sql.Date currentDateSQL = Utilitario.getCurrentDateAndHoursSQL();
		objPublicacion.setFechaAlta(currentDateSQL);
		objPublicacion.setFechaUltModificado(currentDateSQL);
		objPublicacion.setPuntaje(0);
		objPublicacion.setVerificado(false);
		objPublicacion.setHabilitado(true);
		return objPublicacion;
	}

}
