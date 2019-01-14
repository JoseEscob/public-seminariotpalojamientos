package controllersServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.Publicaciones;
import controladoresDAO.Solicitudes;
import controladoresDAO.TiposEstadosSolicitudes;
import exceptions.ServidorException;
import exceptions.CargaViewException;
import extra.Constantes;
import extra.ORSesion;
import modelo.Solicitud;
import modelo.Usuario;
import modelo.TipoEstadoSolicitud;
import views.PaginacionView;
import views.PublicacionView;
import views.SolicitudView;
/**
 * Servlet implementation class SolicitudServlet
 */
@WebServlet("/SolicitudServlet")
public class SolicitudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = null;
	private Solicitudes solicitudDao = new Solicitudes();
	private Publicaciones publicacionDAO = new Publicaciones();
	private TiposEstadosSolicitudes tesDAO = new TiposEstadosSolicitudes();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitudServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String actionPublicacion = request.getParameter(Constantes.accionGET);
			if (actionPublicacion == null) {
				throw new ServidorException("NULL Param: "+Constantes.accionGET+"en SolicitudServlet");
			}

			switch (actionPublicacion) {
			case "SolicitudesReserva":
				//Usamos el mismo metodo que en reservas...
				buscarReservasUsuario(request, response);
				break;
			case "SolicitudesAlojamiento":
				//Usamos el mismo metodo que en reservas...
				buscarAlijamientosUsuario(request, response);
				break;
			}
		} catch (ServidorException e) {
			e.printStackTrace();
		} catch (CargaViewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String actionPublicacion = request.getParameter(Constantes.accionPOST);
			if (actionPublicacion == null) {
				throw new ServidorException("NULL Param: "+Constantes.accionPOST+"en SolicitudServlet");
			}

			switch (actionPublicacion) {
			case "SolicitudesReserva":
				//Usamos el mismo metodo que en reservas...
				buscarReservasUsuario(request, response);
				break;
			case "SolicitudesAlojamiento":
				//Usamos el mismo metodo que en reservas...
				buscarAlijamientosUsuario(request, response);
				break;
			}
		} catch (ServidorException e) {
			e.printStackTrace();
		} catch (CargaViewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buscarReservasUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CargaViewException {
		
		if(ORSesion.sesionActiva(request)) {
			Usuario usuario = ORSesion.getUsuarioBySesion(request);
			ArrayList<SolicitudView> solicitudesVistas = new ArrayList<SolicitudView>();
			ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
			solicitudes = solicitudDao.getByidUsuario(usuario.getIdUsuario());
			for(Solicitud solicitud : solicitudes) {
				if(solicitud.isEsDeReserva()) {
					SolicitudView solicitudVista = new SolicitudView();
					solicitudVista.setSolicitud(solicitud);
					solicitudVista.setUsuario(usuario);
					solicitudVista.setPublicacion(publicacionDAO.getObjectByID(solicitud.getIdPublicacion()));
					TipoEstadoSolicitud tes = new TipoEstadoSolicitud();
					tes.setIdEstadoSolicitud(solicitud.getIdEstadoSolicitud());
					solicitudVista.setEstadoSolicitud(tesDAO.get(tes));
					solicitudesVistas.add(solicitudVista);
				}
			}
			PaginacionView pagination = PaginacionView.crearPaginacion(request.getParameter("Pagina"), solicitudesVistas.size());
			request.setAttribute("paginacion", pagination);
			request.setAttribute("solicitudesReservas", solicitudesVistas);

		}//else = no hay sesion iniciada
		

		paginaJsp = "/Reservas.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
	
	private void buscarAlijamientosUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CargaViewException {
		if(ORSesion.sesionActiva(request)) {
			Usuario usuario = ORSesion.getUsuarioBySesion(request);
			ArrayList<SolicitudView> solicitudesVistas = new ArrayList<SolicitudView>();
			ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
			solicitudes = solicitudDao.getByidUsuario(usuario.getIdUsuario());
			for(Solicitud solicitud : solicitudes) {
				if(!solicitud.isEsDeReserva()) {
					SolicitudView solicitudVista = new SolicitudView();
					solicitudVista.setSolicitud(solicitud);
					solicitudVista.setUsuario(usuario);
					solicitudVista.setPublicacion(publicacionDAO.getObjectByID(solicitud.getIdPublicacion()));
					TipoEstadoSolicitud tes = new TipoEstadoSolicitud();
					tes.setIdEstadoSolicitud(solicitud.getIdEstadoSolicitud());
					solicitudVista.setEstadoSolicitud(tesDAO.get(tes));
					solicitudesVistas.add(solicitudVista);
				}
			}
			PaginacionView pagination = PaginacionView.crearPaginacion(request.getParameter("Pagina"), solicitudesVistas.size());
			request.setAttribute("paginacion", pagination);
			request.setAttribute("solicitudesAlojamientos", solicitudesVistas);

		}//else = no hay sesion iniciada
		

		paginaJsp = "/Alojamientos.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
}
