package controllersServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.SolicitudesDeReserva;
import exceptions.ServidorException;
import extra.Constantes;
import extra.InfoMessage;
import extra.LOG;
import extra.ORSesion;
import modelo.SolicitudDeReserva;
import modelo.Usuario;

/**
 * Servlet implementation class SolDeReservaServlet
 */
@WebServlet("/SolDeReservaServlet")
public class SolDeReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
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
						String.format("NULL Param: %s en SolDeReservaServlet", Constantes.accionGET));
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
		// TODO Auto-generated method stub
		doGet(request, response);

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
		try {
			// 1- Obtiene valores del JSP ya validados
			// 2- Validar con la DB
			// 3- verificar correcto almacenamiento en DB
			// 4- EXITO
			objInfoMessage.setMessage(message);
			objInfoMessage.setEstado(true);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		} finally {
			// 5- Informar estado en interfaz (jsp)
			request.setAttribute("objInfoMessage", objInfoMessage);
			if (objInfoMessage.getEstado())
				paginaJsp = "/solEnviadasRecibidas.jsp";
			else {
				paginaJsp = "/solReservaAlta.jsp";
				request.getSession().setAttribute("objInfoMessage", objInfoMessage);
				//response.sendRedirect(paginaJsp);
			}
			request.getRequestDispatcher(paginaJsp).forward(request, response);
		}
	}

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
