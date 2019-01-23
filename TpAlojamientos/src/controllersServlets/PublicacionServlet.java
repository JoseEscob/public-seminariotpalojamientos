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
import controladoresDAO.Favoritos;
import controladoresDAO.Localidades;
import controladoresDAO.Publicaciones;
import controladoresDAO.Usuarios;
import controladoresDAO.Imagenes;
import controladoresDAO.TiposAlojamientos;
import exceptions.ServidorException;
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
import modelo.Usuario;
import modelo.TipoAlojamiento;
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
			ArrayList<TipoAlojamiento> listaTiposAlojamientos = tipoAlojamientoDAO.getAll();
			
			

			if (request.getParameter("cmbPartido") != null) {
				int idPartido = Integer.parseInt(request.getParameter("cmbPartido"));
				listaLocalidades = localidadDAO.getAllByIdPartido(idPartido);
			}
			request.setAttribute("listaTiposAlojamientos", listaTiposAlojamientos);
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
	private void nuevaPublicacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(ORSesion.sesionActiva(request)) {
			Publicacion publicacion = new Publicacion();
			int idPartido = Integer.parseInt(request.getParameter("partido"));
			int idLocalidad = Integer.parseInt(request.getParameter("localidad"));
			String calle = request.getParameter("calle");
			int altura = Integer.parseInt(request.getParameter("altura"));
			int codPostal = Integer.parseInt(request.getParameter("codPostal"));
			int piso = Integer.parseInt(request.getParameter("piso"));
			String departamento = request.getParameter("departamento");
			//int tipoAlojamiento = Integer.parseInt(request.getParameter("tipoAlojamiento"));
			int superficieCubierta = Integer.parseInt(request.getParameter("superficieCubierta"));
			int superficieDescubierta = Integer.parseInt(request.getParameter("superficieDescubierta"));
			int cantidadPersonas = Integer.parseInt(request.getParameter("cantidadPersonas"));
			int cantidadAmbientes = Integer.parseInt(request.getParameter("cantidadAmbientes"));
			int cantidadDormitorios = Integer.parseInt(request.getParameter("cantidadDormitorios"));
			int cantidadBanios = Integer.parseInt(request.getParameter("cantidadBaños"));
			boolean expensas = Boolean.parseBoolean(request.getParameter("chkExpensas"));
			int precioExpensas = Integer.parseInt(request.getParameter("precioExpensas"));
			int precioNoche = Integer.parseInt(request.getParameter("precioNoche"));
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			
			
			/*System.out.println("partido "+request.getParameter("partido"));
			System.out.println("localidad "+request.getParameter("localidad"));
			System.out.println("calle "+request.getParameter("calle"));
			System.out.println("altura "+request.getParameter("altura"));
			System.out.println("codPostal "+request.getParameter("codPostal"));
			System.out.println("piso "+request.getParameter("piso"));
			System.out.println("departamento "+request.getParameter("departamento"));
			System.out.println("tipoAlojamiento "+request.getParameter("tipoAlojamiento"));	
			
			System.out.println("superficieCubierta "+request.getParameter("superficieCubierta"));
			System.out.println("superficieDescubierta "+request.getParameter("superficieDescubierta"));
			System.out.println("cantidadPersonas "+request.getParameter("cantidadPersonas"));
			System.out.println("cantidadAmbientes "+request.getParameter("cantidadAmbientes"));
			System.out.println("cantidadDormitorios "+request.getParameter("cantidadDormitorios"));
			System.out.println("cantidadBaños "+request.getParameter("cantidadBaños"));
			System.out.println("chkExpensas "+request.getParameter("chkExpensas"));
			System.out.println("precioExpensas "+request.getParameter("precioExpensas"));
			System.out.println("precioNoche "+request.getParameter("precioNoche"));*/

			
			publicacion.setIdUsuario(ORSesion.getUsuarioBySession(request).getIdUsuario());
			publicacion.setIdLocalidad(idLocalidad);
			publicacion.setVerificado(false);
			publicacion.setCalle(calle);
			publicacion.setAltura(altura);
			publicacion.setCodPostal(codPostal);
			publicacion.setPiso(piso);
			publicacion.setDpto(departamento);
			publicacion.setSupCubierta(superficieCubierta);
			publicacion.setSupDescubierta(superficieDescubierta);
			publicacion.setCantPersonas(cantidadPersonas);
			publicacion.setCantAmbientes(cantidadAmbientes);
			publicacion.setCantHabitaciones(cantidadDormitorios);
			publicacion.setCantBanios(cantidadBanios);
			publicacion.setPrecioExpensas(precioExpensas);
			publicacion.setPrecioNoche(precioNoche);
			
			publicacion.setNombre(nombre);
			publicacion.setDescripcion(descripcion);
			publicacion.setFechaAlta(null);
			
			
			/***DATOS*QUE*FALTAN**/
			String nulo = "null";
			publicacion.setCoordenadas(nulo);
			publicacion.setIdTipoAlojamiento(1);
			
			System.out.println(publicacion.toString());
			
			 
			if(publicacionDAO.insert(publicacion)){
				String mensaje = "Publicacion cargada con exito.";
				request.setAttribute("objMensaje", mensaje);
				paginaJsp = "/Publicaciones.jsp";
			
			} 
			  
		
			request.getRequestDispatcher(paginaJsp).forward(request, response);
			
		}
	}

}
