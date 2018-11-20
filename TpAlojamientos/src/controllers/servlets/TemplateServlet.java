package controllers.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class TemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TemplateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("btnNuevoUsuario") != null) {
			Usuario obj = new Usuario();
			// 1- recuperar valores del formulario JSP
			String mail = request.getParameter("mail").toString();
			String claveUno = request.getParameter("claveUno").toString();
			String claveDos = request.getParameter("claveDos").toString();
			String nombre = request.getParameter("nombre").toString();
			String apellido = request.getParameter("apellido").toString();
			Date fechaNac = Date.valueOf(request.getParameter("fechaNac"));
			boolean sexo = Boolean.valueOf(request.getParameter("sexo"));
			String dni = request.getParameter("dni").toString();
			String telefono = request.getParameter("telefono").toString();
			// 2- validar información obtenida JSP
			// 3- guardar información validada DAO
			// 4- verificar correcto almacenamiento en DB
			// 5- Informar estado

			// boolean success = estudianteDAO.actualizarEstudiante(estudiante);
			// String message = null;
			// if (success) {
			// message = "Registro actualizado satisfactoriamente.";
			// }
			// List<Estudiante> listaEstudiantes = estudianteDAO.obtenerEstudiantes();
			// req.setAttribute("idEstudiante", idEstudiante);
			// req.setAttribute("message", message);
			// mostrarListaEstudiantes(req, resp, listaEstudiantes);

		}
		doGet(request, response);
	}

}
