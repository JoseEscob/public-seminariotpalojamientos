package controllersServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.Partidos;
import controladoresDAO.Comentarios;
import controladoresDAO.Localidades;
import controladoresDAO.Publicaciones;
import exceptions.ServidorException;
import extra.LOG;
import modelo.Comentario;
import modelo.Localidad;
import modelo.Partido;

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
			String accion = request.getParameter("accion");
			if (accion == null) {
				throw new ServidorException("NULL Param: accion");
			}

			switch (accion) {
			case "Nuevo":
				cargarComponentesAltaPublicacion(request, response);
				// altaPublicacion(request, response);
				break;
			case "VerComentarios":
				comentariosPublicacion(request, response);
				break;
			}
		} catch (ServidorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		try {
			String actionPublicacion = request.getParameter("actionPublicacion");
			if (actionPublicacion == null) {
				throw new ServidorException("NULL Param: actionPublicacion");
			}

			switch (actionPublicacion) {
			case "cmbPartidoSubmit":
				LOG.info("entro por POST");
				cargarComponentesAltaPublicacion(request, response);
				break;
			case "create":
				cargarComponentesAltaPublicacion(request, response);
				// altaPublicacion(request, response);
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
		// 2- validar información obtenida JSP
		// 2.2 Validar con la DB
		// 3- guardar información validada
		// 4- verificar correcto almacenamiento en DB
		// 5- Informar estado en interfaz (jsp)
		String message = null;

		try {
			ArrayList<Partido> listaPartidos = partidosDAO.getAll();
			ArrayList<Localidad> listaLocalidades = null;

			if (request.getParameter("cmbPartido") != null) {
				int idPartido = Integer.parseInt(request.getParameter("cmbPartido"));
				listaLocalidades = localidadDAO.getByIdPartido(idPartido);
			}

			request.setAttribute("listaPartidos", listaPartidos);
			request.setAttribute("listaLocalidades", listaLocalidades);
			message = "Se cargó el cmb";
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
		// 2- validar información obtenida JSP
		// 2.2 Validar con la DB
		// 3- guardar información validada
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

	@SuppressWarnings({ "null", "unused" })
	private void comentariosPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		ArrayList<Comentario> listaComentarios = null;
		try {
			Comentario objComentarioPub = null;
			int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			objComentarioPub.setIdPublicacion(idPublicacion);

			listaComentarios = comentarioDAO.getAll();
			ArrayList<Comentario> listaComentariosFiltrada = null;

			listaComentarios.forEach(item -> {
				if (item.getIdPublicacion() == idPublicacion)
					listaComentariosFiltrada.add(item);
			});

			if (listaComentariosFiltrada != null)
				message = "Se filtró la lista";
			else
				message = "ERROR al filtrar la lista de Comentarios para la publicación: " + idPublicacion;
			listaComentarios = listaComentariosFiltrada;
			request.setAttribute("listaComentarios", listaComentarios);

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionComentarios.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

}
