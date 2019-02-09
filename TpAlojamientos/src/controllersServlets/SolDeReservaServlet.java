package controllersServlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.SolicitudesDeReserva;
import exceptions.ServidorException;
import exceptions.ValidacionException;
import extra.Constantes;
import extra.InfoMessage;
import extra.LOG;
import extra.ORSesion;
import extra.Utilitario;
import modelo.SolicitudDeReserva;
import modelo.Usuario;
import views.PublicacionView;

/**
 * Servlet implementation class SolDeReservaServlet
 */
@WebServlet("/SolDeReservaServlet")
public class SolDeReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
	private static final String strVistaPublicacion = "vistaPublicacion";
	private final SolicitudesDeReserva solDeReservaDAO = new SolicitudesDeReserva();

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
			case "SolReservaAlta":
				altaSolicitudReserva(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado()) {
				// paginaJsp = "/solEnviadasRecibidas.jsp";
				paginaJsp = "SolDeReservaServlet?accionGET=verSolEnviadasRecibidas";
				request.getSession().removeAttribute(strVistaPublicacion);
				response.sendRedirect(paginaJsp);
			} else {
				// paginaJsp = "/solReservaAlta.jsp";
				paginaJsp = "PublicacionServlet?accionGET=VerPublicacion&idPublicacion=" + idPublicacion;
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				response.sendRedirect(paginaJsp);
			}
			// request.getRequestDispatcher(paginaJsp).forward(request, response);
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

		// 3- Validar que la publicación no esté reservada
		// TODO

		// 4- Obtener el resto de información necesaria
		PublicacionView vistaPublicacion = (PublicacionView) request.getSession().getAttribute(strVistaPublicacion);

		// 4.1) Validar si puede variar la cantidad de personas
		boolean chkPuedeVariarCantPersonas = vistaPublicacion.getPublicacion().isChkPuedeVariarCantPersonas();
		int cantPersonasPermitidas = vistaPublicacion.getPublicacion().getCantPersonas();
		int cantPersonasSolicitadas = Integer.parseInt(request.getParameter("cmbCantHuespedes"));

		if (chkPuedeVariarCantPersonas == false) {
			if (cantPersonasSolicitadas > cantPersonasPermitidas)
				throw new ValidacionException(
						"Esta publicación no permite superar el límite de personas/ huéspedes establecido");
		}

		int precioNoche = vistaPublicacion.getPublicacion().getPrecioNoche();
		int precioExpensas = vistaPublicacion.getPublicacion().getPrecioExpensas();
		int cantDiasReserva = Utilitario.getCantOfDays(fechaReservaInicio, fechaReservaFin);
		// 5- Preparar presupuesto para el precio final
		int precioFinal = cantDiasReserva * precioNoche + precioExpensas;

		// 6- Guardar resto de la información en variables
		int idSolicitud = solDeReservaDAO.getAll().size() + 1;
		int idUsuarioLogueado = ORSesion.getUsuarioBySession(request).getIdUsuario();
		int idPublicacion = vistaPublicacion.getPublicacion().getIdPublicacion();

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

}
