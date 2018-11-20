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
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioServlet() {
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
			// 3- guardar información validada
			obj.setMail(mail);
			obj.setClaveUsuario(claveUno);
			obj.setNombre(nombre);
			obj.setApellido(apellido);
			obj.setFechaNac(fechaNac);
			obj.setSexo(sexo);
			obj.setDni(dni);// obj.setTelefono(telefono);
			// 4- verificar correcto almacenamiento en DB
			// 5- Informar estado
		}
		doGet(request, response);
	}

}
