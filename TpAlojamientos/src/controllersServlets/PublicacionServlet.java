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
import extra.Constantes;
import extra.LOG;
import modelo.Comentario;
import modelo.Favorito;
import modelo.Localidad;
import modelo.Partido;
import modelo.Publicacion;
import modelo.Imagen;
import modelo.Usuario;
import views.ComentarioView;
import views.PublicacionView;

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
	int cantidadComentarios = 0;

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
				throw new ServidorException("NULL Param: "+Constantes.accionGET+" en PublicacionServlet");
			}
			LOG.info("JSP - Acci�n GET: " + accionGET);
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
			//Cambio de actionPublicacion a Constantes.accionPOST
			String actionPublicacion = request.getParameter(Constantes.accionPOST);
			if (actionPublicacion == null) {
				throw new ServidorException("NULL Param: "+Constantes.accionPOST+" en PublicacionServlet");
			}

			switch (actionPublicacion) {
			case "getLocalidades": 
				//nombre provisorio
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
				comentariosPublicacion(request, response);
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
		// 2- validar informaci�n obtenida JSP
		// 2.2 Validar con la DB
		// 3- guardar informaci�n validada
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
			message = "Se carg� el cmb";
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
		// 2- validar informaci�n obtenida JSP
		// 2.2 Validar con la DB
		// 3- guardar informaci�n validada
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

	@SuppressWarnings({ "unused" })
	private void comentariosPublicacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		ArrayList<Comentario> listaComentarios = null;
		ArrayList<Comentario> listaComentariosFiltrada = new ArrayList<Comentario>();
		float publicacionPuntaje = 0;
		try {
			int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			
			publicacionPuntaje = publicacionDAO.getObjectByID(idPublicacion).getPuntaje();
			
			listaComentarios = comentarioDAO.getAll();
			listaComentarios.forEach(item -> {
				if (item.getIdPublicacion() == idPublicacion)
					listaComentariosFiltrada.add(item);
			});

			if (listaComentariosFiltrada != null)
				message = listaComentariosFiltrada.isEmpty() ? "Est� vac�a" : "Se filtr� la lista";
			else
				message = "ERROR al filtrar la lista de Comentarios para la publicaci�n: " + idPublicacion;
			listaComentarios = listaComentariosFiltrada;
			request.setAttribute("listaComentarios", listaComentarios);
			request.setAttribute("publicacionPuntaje", publicacionPuntaje);

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			request.setAttribute("message", message);
			paginaJsp = "/PublicacionComentarios.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}
	
	
	/**********************************************************************/
	/*******************************AJAX***********************************/
	/**********************************************************************/

	private void cargarLocalidadesAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// DEBE REALIZARSE EN ALGUNA FUNCION QUE NO HAGA REDIRIGIR LA PAGINA A OTRA.
		
		if(request.getParameter("idPartido") != null) {
		
			Map<String, Object> resultMap = new HashMap<String, Object>();
			ArrayList<Localidad> localidades = new ArrayList<Localidad>();
			
			int idPartido = Integer.parseInt(request.getParameter("idPartido"));
			localidades = localidadDAO.getByIdPartido(idPartido);
			
			if(localidades != null) {
				resultMap.put("localidades", localidades);				
			}
			else {
				resultMap.put("error", "no se pudo realizar la carga de localidaes");				
			}
		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(new Gson().toJson(resultMap)); //<----- AJAX RESPONDE SIN REDIRIGIR
		}
		
	}
	
	private void verPublicacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		PublicacionView vistaPublicacion = new PublicacionView();
		ArrayList<Imagen> temp = new ArrayList<Imagen>();
		ArrayList<ComentarioView> comentariosPublicacion = new ArrayList<ComentarioView>();
		cantidadComentarios = 0;
		
		if(request.getParameter("idPublicacion") != null) {
			//Primero buscamos la publicacion
			int idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
			Publicacion mostrar = publicacionDAO.getObjectByID(idPublicacion);
			if(mostrar != null) {
				request.setAttribute("publicacion", mostrar);
			
				vistaPublicacion.setPublicacion(mostrar);
				//Buscamos los datos del usuario de la publicacion
				Usuario u = new Usuario();
				u.setIdUsuario(mostrar.getIdUsuario());
				Usuario usuario = usuarioDAO.get(u);
				if(usuario != null) {
					request.setAttribute("usuarioPublicacion", usuario);
					vistaPublicacion.setUsuario(usuario);
				}
			
				//Ahora buscamos las rutas de las imagenes de la publicacion
				ArrayList<Imagen> imagenes = imagenDAO.getAll();
				if(imagenes != null) {
					imagenes.forEach(item -> {
						if (item.getIdPublicacion() == idPublicacion)
							temp.add(item);
					});
					vistaPublicacion.setImagenes(temp);
				}
				//Despues buscamos la cantidad de comentarios
				ArrayList<Comentario> comentarios = comentarioDAO.getAll();
				if(comentarios != null) {
					comentarios.forEach(item -> {
						if(item.getIdPublicacion() == idPublicacion) 
							cantidadComentarios++;
					});
					vistaPublicacion.setComentarios(cantidadComentarios);
				}
				
				request.setAttribute("vistaPublicacion", vistaPublicacion);
			
			} //Validaciones del else?	
			
		} //Validaciones del else?
		
		paginaJsp = "/Publicacion.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
		
	}
	private void verPublicaciones(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		ArrayList<Publicacion> publicaciones = publicacionDAO.getAll();
		if(publicaciones != null) {
			request.setAttribute("publicaciones", publicaciones);
		} //Validaciones del else?
		//Buscar las imagenes
		//Buscar los usuarios correspondientes
		
		paginaJsp = "/Publicaciones.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);		
	}

}
