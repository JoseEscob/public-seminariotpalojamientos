package controllersServlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladoresDAO.Usuarios;
import exceptions.ValidacionException;
import extra.Constantes;
import extra.LOG;
import extra.Utilitario;
import modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = "/UsuarioAlta.jsp";
	private Usuarios usuarioDAO = new Usuarios();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("buscarAction");
		LOG.info("JSP - Acción: " + action);
		if (action != null) {
			switch (action) {
			case "nuevo":
				AltaUsuario(request, response);
				break;
			case "editar":
				// buscarPorNombre(request, response);
				break;
			case "listar":
				// nuevoEstudiante(request, response);
				break;
			}
		}
	}

	private void AltaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario obj = new Usuario();
		String message = null;

		try {
			// 1- recuperar valores del formulario JSP
			String mail = request.getParameter("mail").toString();
			String claveUno = request.getParameter("claveUno").toString();
			String claveDos = request.getParameter("claveDos").toString();
			String nombre = request.getParameter("nombre").toString();
			String apellido = request.getParameter("apellido").toString();
			String fechaNac = request.getParameter("fechaNac");
			boolean sexo = Boolean.valueOf(request.getParameter("sexo"));
			String dni = request.getParameter("dni").toString();
			// String telefono = request.getParameter("telefono").toString();

			// 2- validar información obtenida JSP
			if (!claveUno.equals(claveDos)) {
				throw new ValidacionException("Las claves son diferentes. Por favor revisar que sean identicas");
			}
			if (!Utilitario.esMayorDeEdad(fechaNac)) {
				throw new ValidacionException("El usuario debe ser mayor de edad");
			}

			// 3- guardar información validada
			obj.setMail(mail);
			obj.setClaveUsuario(claveUno);
			obj.setNombre(nombre);
			obj.setApellido(apellido);
			obj.setFechaNac(Utilitario.textoAFecha(fechaNac));
			obj.setSexo(sexo);
			obj.setDni(dni);// obj.setTelefono(telefono);

			// 4- verificar correcto almacenamiento en DB
			if (!usuarioDAO.insert(obj))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");
			// ÉXITO
			message = Constantes.REGISTROEXITOSO;

		} catch (Exception e) {
			message = e.getMessage();
		} finally {
			// 5- Informar estado
			request.setAttribute("message", message);

			paginaJsp = "/UsuarioAlta.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
			dispatcher.forward(request, response);
		}
	}

}
