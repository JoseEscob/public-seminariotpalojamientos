package controllersServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServidorException;

import extra.Constantes;
import modelo.Usuario;
import modelo.Solicitud;
import controladoresDAO.Solicitudes;

/**
 * Servlet implementation class ReservaServlet
 */
@WebServlet("/ReservaServlet")
public class ReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
	private Solicitudes solicitudDao = new Solicitudes();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		buscarReservasUsuario(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String actionPublicacion = request.getParameter(Constantes.accionPOST);
			if (actionPublicacion == null) {
				throw new ServidorException("NULL Param: "+Constantes.accionPOST+" en ReservaServlet");
			}

			switch (actionPublicacion) {
			case "toReservasPage":
				buscarReservasUsuario(request, response);
				break;
			case "toSoliciudesPage":
				buscarReservasUsuario(request, response);
				break;
				default:break;
			}
		} catch (ServidorException e) {
			e.printStackTrace();
		}
	}
	
	private void buscarReservasUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute(Constantes.sessionUser) != null) {
			//Hay sesion activa
			Usuario usuario = new Usuario();
			ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
			ArrayList<Solicitud> reservas = new ArrayList<Solicitud>();			
			solicitudes = solicitudDao.getByidUsuario(usuario.getIdUsuario());
			if(!solicitudes.isEmpty()) {
				//Hay solicitudes
				for(Solicitud solicitud: solicitudes) {
					if(solicitud.isEsDeReserva()) {
						reservas.add(solicitud);
					}
				}
				request.setAttribute("solicitudesReservas", reservas);
			}			
		}
		paginaJsp = "/Reservas.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
private void buscarSolicitudesReservassUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute(Constantes.sessionUser) != null) {
			//Hay sesion activa
			Usuario usuario = new Usuario();
			ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
			ArrayList<Solicitud> alojamientos = new ArrayList<Solicitud>();
			solicitudes = solicitudDao.getByidUsuario(usuario.getIdUsuario());
			if(!solicitudes.isEmpty()) {
				//Hay solicitudes
				for(Solicitud solicitud: solicitudes) {
					if(!solicitud.isEsDeReserva()) {
						alojamientos.add(solicitud);
					}
				}
				request.setAttribute("solicitudesAlojamientos", alojamientos);
			}			
		}
		paginaJsp = "/Solicitudes.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}

}
