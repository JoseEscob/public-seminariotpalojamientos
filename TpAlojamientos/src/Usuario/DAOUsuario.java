package Usuario;

import java.util.List;

import org.hibernate.Session;

import Controlador.ConexionHB;
import Modelo.Usuario;

public class DAOUsuario {
	public static void addNuevoUsuario(Usuario user) {
		Session session = ConexionHB.iniciarConexion().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		ConexionHB.cerrarConexion();
	}

	public static void readAllUsuario() {
		Session session = ConexionHB.iniciarConexion().openSession();
		@SuppressWarnings("unchecked")
		List<Usuario> lista = (List<Usuario>) session.createQuery(" FROM Usuario ");

		for (Usuario obj : lista) {
			System.out.println(obj.toString());
		}
		session.close();
		ConexionHB.cerrarConexion();
	}
}
