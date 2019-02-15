package controllersServlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.Comprobantes;
import controladoresDAO.Publicaciones;
import controladoresDAO.SolicitudesDeReserva;
import controladoresDAO.Usuarios;
import exceptions.ServidorException;
import exceptions.ValidacionException;
import extra.Constantes;
import extra.InfoMessage;
import extra.LOG;
import extra.ORSesion;
import extra.Utilitario;
import modelo.Comprobante;
import modelo.Publicacion;
import modelo.PublicacionReservada;
import modelo.SolicitudDeReserva;
import modelo.Usuario;
import views.PublicacionView;
import views.SolicitudDeReservaView;

/**
 * Servlet implementation class SolDeReservaServlet
 */
@WebServlet("/SolDeReservaServlet")
public class SolDeReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
	private static final String strVistaPublicacion = "vistaPublicacion";
	private final SolicitudesDeReserva solDeReservaDAO = new SolicitudesDeReserva();
	private final Usuarios usuariosDAO = new Usuarios();
	private final Comprobantes comprobantesDAO = new Comprobantes();
	private final Publicaciones publicacionDAO = new Publicaciones();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SolDeReservaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String accionGET = request.getParameter(Constantes.accionGET);
			if (accionGET == null) {
				throw new ServidorException(
						String.format("NULL Param GET: %s en SolDeReservaServlet", Constantes.accionGET));
			}
			LOG.info(String.format("%s GET: %s", Constantes.logJSPAccion, accionGET));
			switch (accionGET) {
			case "verSolEnviadasRecibidas":
				verSolEnviadasRecibidas(request, response);
				break;
			case "cargarSolDeReservasRecibidasByIdPublicacion":
				cargarSolDeReservasRecibidasByIdPublicacion(request, response);
				break;
			case "verFechasDeReservaPublicacion":
				verFechasDeReservaPublicacion(request, response);
				break;
			case "verListadoComprobanteDeReserva":
				verListadoComprobanteDeReserva(request, response);
				break;
			case "verComprobanteDeReserva":
				verComprobanteDeReserva(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub doGet(request, response);

		try {
			String accionPOST = request.getParameter(Constantes.accionPOST);
			if (accionPOST == null) {
				throw new ServidorException(
						String.format("NULL Param: %s en SolDeReservaServlet", Constantes.accionPOST));
			}
			LOG.info(String.format("%s POST: %s", Constantes.logJSPAccion, accionPOST));
			switch (accionPOST) {

			case "altaSolicitudReserva":
				altaSolicitudReserva(request, response);
				break;
			case "validarAprobacionDeSolicitudes":
				validarAprobacionDeSolicitudes(request, response);
				break;
			case "aprobarSolicitudesDeReserva":
				aprobarSolicitudesDeReserva(request, response);
				break;
			case "rechazarSolicitudesDeReserva":
				rechazarSolicitudesDeReserva(request, response);
				break;
			case "cancelarSolicitudDeReserva":
				cancelarSolicitudDeReserva(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verFechasDeReservaPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		int idPublicacion = 0;
		try {
			// 1- Obtiene valores del JSP ya validados
			/*
			 * if (request.getSession().getAttribute(strVistaPublicacion) == null) { throw
			 * new ValidacionException("NULL ERROR - verificar el atributo: " +
			 * strVistaPublicacion); } PublicacionView vistaPublicacion = (PublicacionView)
			 * request.getSession().getAttribute(strVistaPublicacion); idPublicacion =
			 * vistaPublicacion.getPublicacion().getIdPublicacion(); // TODO Eliminar codigo
			 * de arriba
			 */
			if (request.getParameter("idPublicacion") == null) {
				throw new ServidorException("ERROR NULL Parameters: idPublicacion");
			}
			idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));

			ArrayList<PublicacionReservada> listaFechasReservadas = comprobantesDAO
					.getListadoDeFechasReservaPublicacion(idPublicacion);
			ArrayList<PublicacionReservada> listaFechasReservadasDesdeHoy = new ArrayList<PublicacionReservada>();
			java.sql.Date fechaActualSQL = Utilitario.getCurrentDateAndHoursSQL();

			// comparar solo los registros que al día de la fecha estén reservadas
			listaFechasReservadas.forEach(item -> {
				if (item.getFechaReservaFin().after(fechaActualSQL))
					listaFechasReservadasDesdeHoy.add(item);
			});

			request.setAttribute("listaFechasReservadasDesdeHoy", listaFechasReservadasDesdeHoy);
			// 5- Informar estado en interfaz (jsp)
			message = "Se obtuvo con éxito las fechas de reserva de la publicación: " + idPublicacion;
			LOG.info(message);
			objInfoMessage.setMessage(message);
			objInfoMessage.setEstado(true);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/PublicacionVerFechasReservadas.jsp";
				request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" + idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}
	}

	private void altaSolicitudReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		int idPublicacion = 0;
		try {
			// 1- Obtiene valores del JSP ya validados
			if (request.getSession().getAttribute(strVistaPublicacion) == null) {
				throw new ValidacionException("NULL ERROR - verificar el atributo: " + strVistaPublicacion);
			}
			PublicacionView vistaPublicacion = (PublicacionView) request.getSession().getAttribute(strVistaPublicacion);
			idPublicacion = vistaPublicacion.getPublicacion().getIdPublicacion();
			// TODO Eliminar codigo de arriba
			SolicitudDeReserva objSolDeReserva = getObjectSolDeReservaByJSPData(request);
			// 2- Validar con la DB
			if (!solDeReservaDAO.insert(objSolDeReserva))
				throw new ValidacionException("SQL: Ocurrió un error al guardar la solicitud");
			// 3- verificar correcto almacenamiento en DB
			// 4- EXITO
			message = "Se generó con éxito la solicitud de reserva nro: " + objSolDeReserva.getIdSolicitud();
			LOG.info(message);
			objInfoMessage.setMessage(message);
			objInfoMessage.setEstado(true);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			if (objInfoMessage.getEstado()) {
				paginaJsp = "SolDeReservaServlet?accionGET=verSolEnviadasRecibidas";
				request.getSession().removeAttribute(strVistaPublicacion);
				response.sendRedirect(paginaJsp);
			} else {
				paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" + idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}
	}

	private SolicitudDeReserva getObjectSolDeReservaByJSPData(HttpServletRequest request) throws ValidacionException {
		// 1- Validar parámetros del JSP
		if (!ORSesion.sesionActiva(request)) {
			throw new ValidacionException("No se encontró iniciada la sesión del usuario");
		}
		if (request.getParameter("cmbCantHuespedes") == null) {
			throw new ValidacionException("Por favor complete el campo obligatorio: Cant. de huéspedes");
		}
		if (request.getParameter("fechaInicio") == null) {
			throw new ValidacionException("Por favor complete el campo obligatorio: Fecha de Inicio");
		}
		if (request.getParameter("fechaFin") == null) {
			throw new ValidacionException("Por favor complete el campo obligatorio: Fecha de Fin");
		}

		if (request.getSession().getAttribute(strVistaPublicacion) == null) {
			throw new ValidacionException("NULL ERROR - verificar el atributo: " + strVistaPublicacion);
		}
		// 2- Validar que las fechas sean válidas
		Date fechaReservaInicio = java.sql.Date.valueOf(request.getParameter("fechaInicio").toString());
		Date fechaReservaFin = java.sql.Date.valueOf(request.getParameter("fechaFin").toString());

		if (fechaReservaInicio.before(Utilitario.getCurrentDateAndHoursSQL())) {
			throw new ValidacionException("La Fecha de Inicio debe ser mayor a la fecha de hoy");
		}

		if (fechaReservaInicio.after(fechaReservaFin)) {
			throw new ValidacionException("La Fecha de Inicio debe ser menor a la Fecha de Fin");
		}

		if (fechaReservaInicio.equals(fechaReservaFin)) {
			throw new ValidacionException("La Fecha de Inicio no puede ser igual a la Fecha de Fin");
		}

		// 3- Obtener el resto de información necesaria
		PublicacionView vistaPublicacion = (PublicacionView) request.getSession().getAttribute(strVistaPublicacion);
		int idPublicacion = vistaPublicacion.getPublicacion().getIdPublicacion();
		// 4.1) Validar si puede variar la cantidad de personas
		boolean chkPuedeVariarCantPersonas = vistaPublicacion.getPublicacion().isChkPuedeVariarCantPersonas();
		int cantPersonasPermitidas = vistaPublicacion.getPublicacion().getCantPersonas();
		int cantPersonasSolicitadas = Integer.parseInt(request.getParameter("cmbCantHuespedes"));

		if (chkPuedeVariarCantPersonas == false) {
			if (cantPersonasSolicitadas > cantPersonasPermitidas)
				throw new ValidacionException(
						"Esta publicación no permite superar el límite de personas/ huéspedes establecido");
		}
		// 4.2) Validar que la publicación no esté reservada
		validarQuelaPublicacionNoEsteReservada(idPublicacion, fechaReservaInicio);
		validarQuelaPublicacionNoEsteReservada(idPublicacion, fechaReservaFin);

		// 5.1- Obtener información necesaria para el presupuesto
		int precioNoche = vistaPublicacion.getPublicacion().getPrecioNoche();
		int precioExpensas = vistaPublicacion.getPublicacion().getPrecioExpensas();
		int cantDiasReserva = Utilitario.getCantOfDays(fechaReservaInicio, fechaReservaFin);
		// 5.2- Preparar presupuesto para el precio final
		// Las expensas solo se cobraran si la cantidad de días supera los 25
		int precioFinal = cantDiasReserva * precioNoche;
		if (cantDiasReserva > 25)
			precioFinal = precioFinal + precioExpensas;
		// 6- Guardar resto de la información en variables
		int idSolicitud = solDeReservaDAO.getAll().size() + 1;
		int idUsuarioLogueado = ORSesion.getUsuarioBySession(request).getIdUsuario();
		String fechaAltaSolicitud = Utilitario.getCurrentDateAndHoursString();
		int idUsuarioPropietario = vistaPublicacion.getUsuario().getIdUsuario();
		String fechaDecisionPropietario = null;
		String motivoDecisionPropietario = null;
		int idEstadoSolicitud = 1; // Iniciada - Pendiente confirmación
		boolean habilitado = true;

		// 7- Guardar la info validada en el obj
		SolicitudDeReserva objSolDeReserva = new SolicitudDeReserva();
		objSolDeReserva.setIdSolicitud(idSolicitud);
		objSolDeReserva.setIdUsuarioHuesped(idUsuarioLogueado);
		objSolDeReserva.setIdPublicacion(idPublicacion);
		objSolDeReserva.setFechaReservaInicio(fechaReservaInicio);
		objSolDeReserva.setFechaReservaFin(fechaReservaFin);
		objSolDeReserva.setCantPersonas(cantPersonasSolicitadas);
		objSolDeReserva.setPrecioFinal(precioFinal);
		objSolDeReserva.setFechaAltaSolicitud(fechaAltaSolicitud);
		objSolDeReserva.setIdUsuarioPropietario(idUsuarioPropietario);
		objSolDeReserva.setFechaDecisionPropietario(fechaDecisionPropietario);
		objSolDeReserva.setMotivoDecisionPropietario(motivoDecisionPropietario);
		objSolDeReserva.setIdEstadoSolicitud(idEstadoSolicitud);
		objSolDeReserva.setHabilitado(habilitado);

		return objSolDeReserva;
	}

	//
	// request.getParameter("vistaPublicacion");
	// request.getAttribute("vistaPublicacion");
	// request.getSession().getAttribute("vistaPublicacion");

	private void verSolEnviadasRecibidas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- Declaración de variables
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		try {
			// 1- recuperar valores del request y los DAOs
			if (!ORSesion.sesionActiva(request)) {
				throw new ServidorException("No se encontró iniciada la sesión del usuario");
			}
			Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(request);
			// 2- validar información obtenida

			int idUsuarioHuesped;
			int idUsuarioPropietario;
			idUsuarioPropietario = idUsuarioHuesped = objUsuarioLogueado.getIdUsuario();
			// 3- Cargar listado de solicitudes enviadas
			ArrayList<SolicitudDeReserva> listaSolDeReservaEnviada = solDeReservaDAO
					.getAllByIdUsuarioHuespedSorted(idUsuarioHuesped);
			ArrayList<SolicitudDeReservaView> listaSolDeReservaView = new ArrayList<SolicitudDeReservaView>();
			SolicitudDeReservaView objSolReservaView = new SolicitudDeReservaView();
			for (SolicitudDeReserva objSolReserva : listaSolDeReservaEnviada) {
				objSolReservaView = new SolicitudDeReservaView();
				objSolReservaView.setObjSolReserva(objSolReserva);
				objSolReservaView.cargarDatosDePublicacion(objSolReserva.getIdPublicacion());
				listaSolDeReservaView.add(objSolReservaView);
			}
			// 4- Cargar listado de solicitudes recibidas
			// TODO: PublicacionSolRecibidaView -> Publicacion, idEstadoSolicitud,
			// cantSolicitudes
			ArrayList<SolicitudDeReserva> listaSolDeReservaRecibida = solDeReservaDAO
					.getAllByIdUsuarioPropietario(idUsuarioPropietario);
			ArrayList<Publicacion> listaPublicacionSolReservaRecibidas = new ArrayList<Publicacion>();
			int idPublicacionAnterior = 0;
			for (SolicitudDeReserva item : listaSolDeReservaRecibida) {
				Publicacion objPublicacion = new Publicacion();
				if (idPublicacionAnterior == 0 || (item.getIdPublicacion() != idPublicacionAnterior)) {
					if (item.getIdEstadoSolicitud() == 1) {
						idPublicacionAnterior = item.getIdPublicacion();
						objPublicacion = publicacionDAO.getObjectByID(idPublicacionAnterior);
						// 14-02-19 Begin
						objPublicacion.iniciarYcargarObjPublicacionInfo();
						// 14-02-19 End
						listaPublicacionSolReservaRecibidas.add(objPublicacion);
					}
				}
			}

			// 5- Guardar información en request para su posterior muestra/exposición en JSP
			request.setAttribute("listaSolDeReservaView", listaSolDeReservaView);
			request.setAttribute("listaPublicacionSolReservaRecibidas", listaPublicacionSolReservaRecibidas);
			// request.setAttribute("listaSolDeReservaRecibida", listaSolDeReservaRecibida);
			message = "Se cargó su lista de solicitudes con éxito";
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 4- Informar estado/resultados en interfaz (JSP)
			request.getSession().setAttribute("objInfoMessage", objInfoMessage);
			paginaJsp = "/solEnviadasRecibidas.jsp";
			request.getRequestDispatcher(paginaJsp).forward(request, response);
		}
	}

	private void verSolEnviadasRecibidasBackUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0- Declaración de variables
		String message = null;
		try {
			// 1- recuperar valores del request y los DAOs
			if (!ORSesion.sesionActiva(request)) {
				throw new ServidorException("No se encontró iniciada la sesión del usuario");
			}
			Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(request);
			// 2- validar información obtenida

			int idUsuarioHuesped;
			int idUsuarioPropietario;
			idUsuarioPropietario = idUsuarioHuesped = objUsuarioLogueado.getIdUsuario();

			ArrayList<SolicitudDeReserva> listaSolDeReservaEnviada = solDeReservaDAO
					.getAllByIdUsuarioHuesped(idUsuarioHuesped);

			ArrayList<SolicitudDeReserva> listaSolDeReservaRecibida = solDeReservaDAO
					.getAllByIdUsuarioPropietario(idUsuarioPropietario);

			// 3- guardar información en request para su posterior muestra/exposición en JSP
			request.setAttribute("listaSolDeReservaEnviada", listaSolDeReservaEnviada);
			request.setAttribute("listaSolDeReservaRecibida", listaSolDeReservaRecibida);
		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 4- Informar estado/resultados en interfaz (JSP)
			request.setAttribute("message", message);
			paginaJsp = "/solEnviadasRecibidas.jsp";
			request.getRequestDispatcher(paginaJsp).forward(request, response);
		}
	}

	private void verListadoComprobanteDeReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		try {
			// 1- recuperar valores del request y los DAOs
			if (!ORSesion.sesionActiva(request)) {
				throw new ServidorException("No se encontró iniciada la sesión del usuario");
			}
			// 1.1 request: infoUsuarioLogueado
			int idUsuarioHuesped;
			int idUsuarioPropietario;
			idUsuarioPropietario = idUsuarioHuesped = ORSesion.getUsuarioBySession(request).getIdUsuario();
			// 2- Obtener listado de comprobantes del usuario logueado, enviadas y recibidas
			ArrayList<Comprobante> listaComprobantesSolEnviados = new ArrayList<Comprobante>();
			ArrayList<Comprobante> listaComprobantesSolRecibidos = new ArrayList<Comprobante>();
			listaComprobantesSolEnviados = comprobantesDAO.getAllByIdUsuarioHuesped(idUsuarioHuesped);
			listaComprobantesSolRecibidos = comprobantesDAO.getAllByIdUsuarioPropietario(idUsuarioPropietario);
			// 3- Setear las respuestas al request
			request.setAttribute("listaComprobantesSolEnviados", listaComprobantesSolEnviados);
			request.setAttribute("listaComprobantesSolRecibidos", listaComprobantesSolRecibidos);
			message = "Se cargaron sus listas de comprobantes con éxito";
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/solComprobanteListadoTabla.jsp";
				request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=";
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}
	}

	private void verComprobanteDeReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		int idPublicacion = 0;
		try {

			// 1- recuperar valores del request y los DAOs
			if (!ORSesion.sesionActiva(request)) {
				throw new ServidorException("No se encontró iniciada la sesión del usuario");
			}
			// 2- validar información obtenida objComprobante
			int idUsuarioHuesped = 0, idUsuarioPropietario = 0;
			int idComprobante = 1;
			Comprobante objComprobante = comprobantesDAO.getObjectByIdComprobante(idComprobante);

			idUsuarioHuesped = objComprobante.getIdUsuarioHuesped();
			idUsuarioPropietario = objComprobante.getIdUsuarioPropietario();

			request.setAttribute("objComprobante", objComprobante);
			request.setAttribute("objUsuarioHuesped", usuariosDAO.getUsuarioById(idUsuarioHuesped));
			request.setAttribute("objUsuarioProp", usuariosDAO.getUsuarioById(idUsuarioPropietario));
			// 4- EXITO
			message = "Se cargaron los datos del comprobante de reserva nro: " + objComprobante.getIdComprobante();
			LOG.info(message);
			objInfoMessage.setMessage(message);
			objInfoMessage.setEstado(true);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/solComprobanteViewDetails.jsp";
				request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" + idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}
	}

	private void cargarSolDeReservasRecibidasByIdPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		try {
			// 1- recuperar valores del request y los DAOs
			if (!ORSesion.sesionActiva(request)) {
				throw new ServidorException("No se encontró iniciada la sesión del usuario");
			}
			// 1.1 request: idPublicacion
			if (request.getParameter("idPublicacion") == null) {
				throw new ServidorException("El parámetro de idPublicacion es null");
			}
			int idPublicacion = 0;
			idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			// 1.2 request: infoUsuarioLogueado
			int idUsuarioPropietario = 0;
			idUsuarioPropietario = ORSesion.getUsuarioBySession(request).getIdUsuario();
			// 1.3 DAO: solicitudes por alojamiento
			ArrayList<SolicitudDeReserva> listaSolDeReservasRecibidasPorPublicacion = solDeReservaDAO
					.getAllByIdUsuarioPropietarioIdPublicacion(idUsuarioPropietario, idPublicacion);

			for (SolicitudDeReserva objSolDeReserva : listaSolDeReservasRecibidasPorPublicacion) {
				verificarQueElUsuarioLogueadoSeaElPropietario(request, objSolDeReserva);
			}
			// 2- Devolver información al JSP
			request.setAttribute("listaSolDeReservasRecibidasPorPublicacion",
					listaSolDeReservasRecibidasPorPublicacion);
			// 3- Informar estado de transacción
			message = "Se cargaron las solicitudes de la publicación con ID: " + idPublicacion;
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 3- Informar estado en interfaz (jsp)
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/solReservaRecibidaPorPublicacion.jsp";
				request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				paginaJsp = "SolDeReservaServlet?accionGET=verSolEnviadasRecibidas";
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}
	}

	/**
	 * Devuelve una lista de las solicitudes de reserva seleccionadas en un request
	 * 
	 * @throws ValidacionException
	 */
	private ArrayList<SolicitudDeReserva> obtenerListaSolDeReservaSeleccionadas(HttpServletRequest request)
			throws ValidacionException {
		// 1) Validar datos del request del JSP
		if (request.getParameterValues("chklistSolSeleccionadas") == null)
			throw new ValidacionException("Para realizar esta operación debe seleccionar al menos una solicitud");
		// 2) Obtener los id de las solicitudes de reserva seleccionadas
		String[] chklistSolSeleccionadas = request.getParameterValues("chklistSolSeleccionadas");
		ArrayList<Integer> listaIDSolSeleccionadas = new ArrayList<Integer>();
		for (String chkSolSeleccionada : chklistSolSeleccionadas) {
			listaIDSolSeleccionadas.add(Integer.parseInt(chkSolSeleccionada));
		}
		// 3) Recuperar información de los id seleccionados en una lista de objetos
		int cantObjSolDeReservaLeidos = 0;
		ArrayList<SolicitudDeReserva> listaSolDeReservaSeleccionadas = new ArrayList<SolicitudDeReserva>();
		for (int idSolDeReserva : listaIDSolSeleccionadas) {
			SolicitudDeReserva objSolDeReserva = new SolicitudDeReserva();
			objSolDeReserva = solDeReservaDAO.getObjectById(idSolDeReserva);
			listaSolDeReservaSeleccionadas.add(objSolDeReserva);

			cantObjSolDeReservaLeidos++;
		}
		// 4) Informar estado y retornar información procesada
		LOG.info(String.format("Se obtuvieron %d de %d solicitudes seleccionadas", cantObjSolDeReservaLeidos,
				listaIDSolSeleccionadas.size()));

		return listaSolDeReservaSeleccionadas;
	}

	private void validarAprobacionDeSolicitudes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;

		try {
			// 1- Obtener información del JSP (Solicitudes seleccionados) y de las
			// Solicitudes
			ArrayList<SolicitudDeReserva> listaSolDeReservaSeleccionadas = obtenerListaSolDeReservaSeleccionadas(
					request);
			// 2- Verificar que las fechas de las Solicitudes no se superpongan entre sí
			// TODO revisar
			int cantDeSolSeleccionadas = listaSolDeReservaSeleccionadas.size();
			for (int i = 0; i < cantDeSolSeleccionadas; i++) {
				SolicitudDeReserva objSolDeReservaAnterior;
				for (SolicitudDeReserva objSolDeReserva : listaSolDeReservaSeleccionadas) {
					objSolDeReservaAnterior = objSolDeReserva;
					if (objSolDeReserva.getIdSolicitud() != objSolDeReservaAnterior.getIdSolicitud()) {

						Date fechaInicio = objSolDeReservaAnterior.getFechaReservaInicio();
						Date fechaFin = objSolDeReservaAnterior.getFechaReservaFin();

						boolean estaDentroDelRango = isWithinRange(objSolDeReserva.getFechaReservaInicio(), fechaInicio,
								fechaFin);
						if (estaDentroDelRango) {
							// message = String.format("Lo sentimos la fecha %s no está disponible. La
							// publicación está reservada desde el %s hasta el %s", fechaAValidar,
							// objReserva.getFechaReservaInicio(), objReserva.getFechaReservaFin())
							LOG.info(message);
							throw new ValidacionException();
						}

						estaDentroDelRango = isWithinRange(objSolDeReserva.getFechaReservaFin(), fechaInicio, fechaFin);
						if (estaDentroDelRango) {
							// message = String.format("Lo sentimos la fecha %s no está disponible. La
							// publicación está reservada desde el %s hasta el %s", fechaAValidar,
							// objReserva.getFechaReservaInicio(), objReserva.getFechaReservaFin())
							LOG.info(message);
							throw new ValidacionException();
						}

					}
				}
			}
			// 3- Verificar que las fechas solicitadas no exista una reserva
			// 4- DB Actualizar la solicitud en 'solicitudesDeReservas'
			// 4.1 - DB APROBAR: generar registro en 'comprobantes'
			// 5- Informar estado de transacción
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/solComprobanteViewDetails.jsp";
				request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				// paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" +
				// idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}

	}

	private void aprobarSolicitudesDeReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;

		try {
			// 1- Obtener información del JSP (Solicitudes seleccionados) y de las
			// Solicitudes
			ArrayList<SolicitudDeReserva> listaSolDeReservaSeleccionadas = obtenerListaSolDeReservaSeleccionadas(
					request);
			// 2- Validar que sea el usuario propietario quien está realizando la acción
			for (SolicitudDeReserva objSolDeReserva : listaSolDeReservaSeleccionadas) {
				verificarQueElUsuarioLogueadoSeaElPropietario(request, objSolDeReserva);
				// validar que la solicitud siga pendiente de aprobación
				if (objSolDeReserva.getIdEstadoSolicitud() != 1) {
					message = String.format("ADVERTENCIA: la solicitud %d ya no está pendiente de aprobación",
							objSolDeReserva.getIdSolicitud());
					LOG.info(message);
				}
			}
			// A esta instancia las solicitudes seleccionadas fueron validadas
			// 3.1 DB Actualizar la solicitud en 'solicitudesDeReservas'
			int cantSolicitudesActualizadas = 0;
			for (SolicitudDeReserva objSolDeReserva : listaSolDeReservaSeleccionadas) {
				// 3.1.1 Setear objeto antes de realizar la transacción con la DB
				objSolDeReserva.setFechaDecisionPropietario(Utilitario.getCurrentDateAndHoursString());
				objSolDeReserva.setMotivoDecisionPropietario("Solicitud Aprobada");// TODO: extraer a Constantes
				objSolDeReserva.setIdEstadoSolicitud(5); // 5=aprobado - Verificar 'tiposEstadosSolicitudes'
				// 3.1.2 actualizar en DB
				if (!solDeReservaDAO.update(objSolDeReserva))
					throw new ValidacionException(
							"SQL Ocurrió un error al actualizar la solicitud " + objSolDeReserva.getIdSolicitud());
				cantSolicitudesActualizadas++;
			}
			// 3.2 - 'solicitudesDeReservas' Informar estado de transacción
			message = String.format("APROBACION: Se actualizaron %d de %d solicitudes", cantSolicitudesActualizadas,
					listaSolDeReservaSeleccionadas.size());
			LOG.info(message);
			// 4- generar listado de objetos de comprobantes
			ArrayList<Comprobante> listaComprobantesAInsertarDB = new ArrayList<Comprobante>();
			for (SolicitudDeReserva objSolDeReserva : listaSolDeReservaSeleccionadas) {
				Comprobante objComprobante = new Comprobante();
				objComprobante = obtenerDatosDeSolicitudComoComprobante(objSolDeReserva);
				listaComprobantesAInsertarDB.add(objComprobante);
			}
			// 5- 'comprobantes' generar/ insertar registro en DB
			int cantComprobantesInsertados = 0;
			for (Comprobante objComprobante : listaComprobantesAInsertarDB) {
				int idComprobante = comprobantesDAO.getCount() + 1;
				objComprobante.setIdComprobante(idComprobante);
				if (!comprobantesDAO.insert(objComprobante)) {
					message = String.format("SQL Ocurrió un error al insertar el comprobante %d para la solicitud %d",
							objComprobante.getIdComprobante(), objComprobante.getIdSolicitud());
					throw new ValidacionException(message);
				}
				cantComprobantesInsertados++;
			}
			// 6- Informar estado de transacción
			message = String.format("Se generaron %d de %d comprobante/s de reserva", cantComprobantesInsertados,
					listaComprobantesAInsertarDB.size());
			LOG.info(message);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/--.jsp";
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
				// request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				// paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" +
				// idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}
	}

	private Comprobante obtenerDatosDeSolicitudComoComprobante(SolicitudDeReserva objSolDeReserva) {
		Comprobante objComprobante = new Comprobante();
		// setIdComprobante(idComprobante);
		objComprobante.setIdSolicitud(objSolDeReserva.getIdSolicitud());
		objComprobante.setIdUsuarioHuesped(objSolDeReserva.getIdUsuarioHuesped());
		objComprobante.setIdPublicacion(objSolDeReserva.getIdPublicacion());
		objComprobante.setFechaReservaInicio(objSolDeReserva.getFechaReservaInicio());
		objComprobante.setFechaReservaFin(objSolDeReserva.getFechaReservaFin());
		objComprobante.setCantPersonas(objSolDeReserva.getCantPersonas());
		objComprobante.setPrecioFinal(objSolDeReserva.getPrecioFinal());
		objComprobante.setFechaAlta(Utilitario.getCurrentDateAndHoursSQL());
		objComprobante.setIdUsuarioPropietario(objSolDeReserva.getIdUsuarioPropietario());
		objComprobante.setHabilitado(true);
		objComprobante.setCantDiasReserva(objSolDeReserva.getCantDiasReserva());
		return objComprobante;
	}

	private void verificarQueElUsuarioLogueadoSeaElPropietario(HttpServletRequest request,
			SolicitudDeReserva objSolDeReserva) throws ValidacionException {
		// int idPublicacion (por parámetro)
		// Publicacion objPublicacion = publicacionDAO.getObjectByID(idPublicacion);
		// int idUsuarioPropietario = objPublicacion.getIdUsuario();
		int idUsuarioPropietario = objSolDeReserva.getIdUsuarioPropietario();
		int idUsuarioLogueado = 0;
		idUsuarioLogueado = ORSesion.getUsuarioBySession(request).getIdUsuario();

		if (idUsuarioLogueado != idUsuarioPropietario) {
			throw new ValidacionException(
					"Usted no es el propietario de la publicación con ID: " + objSolDeReserva.getIdPublicacion());
		}
	}

	private void rechazarSolicitudesDeReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;

		try {
			// 1- Obtener información del JSP (Solicitudes seleccionados) y de las
			// Solicitudes
			ArrayList<SolicitudDeReserva> listaSolDeReservaSeleccionadas = obtenerListaSolDeReservaSeleccionadas(
					request);
			// 2- Validar que sea el usuario propietario quien está realizando la acción
			for (SolicitudDeReserva objSolDeReserva : listaSolDeReservaSeleccionadas) {
				verificarQueElUsuarioLogueadoSeaElPropietario(request, objSolDeReserva);
				// validar que la solicitud siga pendiente de aprobación
				if (objSolDeReserva.getIdEstadoSolicitud() != 1) {
					message = String.format("ADVERTENCIA: la solicitud %d ya no está pendiente de aprobación",
							objSolDeReserva.getIdSolicitud());
					LOG.info(message);
				}
			}
			// A esta instancia las solicitudes seleccionadas fueron validadas
			// 3.1 DB Actualizar la solicitud en 'solicitudesDeReservas'
			int cantSolicitudesActualizadas = 0;
			for (SolicitudDeReserva objSolDeReserva : listaSolDeReservaSeleccionadas) {
				// 3.1.1 Setear objeto antes de realizar la transacción con la DB
				objSolDeReserva.setFechaDecisionPropietario(Utilitario.getCurrentDateAndHoursString());
				objSolDeReserva.setMotivoDecisionPropietario("Solicitud Rechazada por el Propietario");// TODO: extraer
																										// a Constantes
				objSolDeReserva.setIdEstadoSolicitud(2); // 2:rechazada - Verificar 'tiposEstadosSolicitudes'
				// 3.1.2 actualizar en DB
				if (!solDeReservaDAO.update(objSolDeReserva))
					throw new ValidacionException(
							"SQL Ocurrió un error al actualizar la solicitud " + objSolDeReserva.getIdSolicitud());
				cantSolicitudesActualizadas++;
			}
			// 3.2 - 'solicitudesDeReservas' Informar estado de transacción
			message = String.format("RECHAZO: Se rechazaron %d de %d solicitudes", cantSolicitudesActualizadas,
					listaSolDeReservaSeleccionadas.size());
			LOG.info(message);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado()) {
				paginaJsp = "/--.jsp";
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
				// request.getRequestDispatcher(paginaJsp).forward(request, response);
			} else {
				// paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" +
				// idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
		}

	}

	private void cancelarSolicitudDeReserva(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InfoMessage objInfoMessage = new InfoMessage();
		String message = null;
		int idSolicitud = 0;
		try {
			// 1- Obtener información del JSP (Solicitudes seleccionados) y de las
			// Solicitudes
			if (request.getParameter("idSolicitud") == null) {
				throw new ServidorException("No se encontró la solicitud con ID: " + idSolicitud);
			}
			idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
			SolicitudDeReserva objSolDeReserva = solDeReservaDAO.getObjectById(idSolicitud);
			// 2- Validar que sea el usuario propietario sea quien está realizando la acción
			objSolDeReserva.getIdUsuarioHuesped();
			// 2.1- Validar que la solicitud siga en estado pendiente de aprobación
			if (objSolDeReserva.getIdEstadoSolicitud() != 1) {
				message = String.format("ADVERTENCIA: la solicitud %d ya no está pendiente de aprobación",
						objSolDeReserva.getIdSolicitud());
				LOG.info(message);
			}
			// 3.1- Setear objeto antes de realizar la transacción con la DB
			objSolDeReserva.setIdEstadoSolicitud(3); // 3: 'Cancelada por el solicitador'
			objSolDeReserva.setHabilitado(false);
			// 3.2- DB Actualizar la solicitud en 'solicitudesDeReservas'
			if (!solDeReservaDAO.update(objSolDeReserva))
				throw new ValidacionException(
						"SQL Ocurrió un error al actualizar la solicitud " + objSolDeReserva.getIdSolicitud());

			// 4- 'solicitudesDeReservas' Informar estado de transacción
			message = String.format("La solicitud %d fue cancelada con éxito", objSolDeReserva.getIdSolicitud());
			LOG.info(message);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			// request.setAttribute("objInfoMessage", objInfoMessage);
			paginaJsp = "SolDeReservaServlet?accionGET=verSolEnviadasRecibidas";
			request.getSession().setAttribute("objInfoMessage", objInfoMessage);
			response.sendRedirect(paginaJsp);
		}

	}

	private void validarQuelaPublicacionNoEsteReservada(int idPublicacion, Date fechaAValidar)
			throws ValidacionException {
		// https://stackoverflow.com/questions/883060/how-can-i-determine-if-a-date-is-between-two-dates-in-java
		// https://www.daniweb.com/programming/web-development/threads/252295/display-table-row-with-a-check-box-and-passing-each-selected-value
		ArrayList<PublicacionReservada> listaFechasReserva = comprobantesDAO
				.getListadoDeFechasReservaPublicacion(idPublicacion);
		// TODO
		java.sql.Date fechaActualSQL = Utilitario.getCurrentDateAndHoursSQL();
		String message;
		for (PublicacionReservada objReserva : listaFechasReserva) {
			// comparar solo los registros que al día de la fecha estén reservadas
			if (objReserva.getFechaReservaFin().after(fechaActualSQL)) {
				boolean estaDentroDelRango = isWithinRange(fechaAValidar, objReserva.getFechaReservaInicio(),
						objReserva.getFechaReservaFin());
				if (estaDentroDelRango) {
					message = String.format(
							"Lo sentimos la fecha %s no está disponible. La publicación está reservada desde el %s hasta el %s",
							fechaAValidar, objReserva.getFechaReservaInicio(), objReserva.getFechaReservaFin());
					throw new ValidacionException(message);
				}
			}

		}

	}

	private boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
		return !(testDate.before(startDate) || testDate.after(endDate));
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ---------------------------------------- Date a, b; // assume these are set
	 * to something Date d; // the date in question
	 * 
	 * return a.compareTo(d) * d.compareTo(b) > 0; // return a.compareTo(d) *
	 * d.compareTo(b) >= 0;
	 * 
	 * ---------------------------
	 * 
	 * boolean isWithinRange(Date testDate) { return !(testDate.before(startDate) ||
	 * testDate.after(endDate)); } //Doesn't seem that awkward to me. Note that I
	 * wrote it that way instead of
	 * 
	 * //return testDate.after(startDate) && testDate.before(endDate);
	 */

}
