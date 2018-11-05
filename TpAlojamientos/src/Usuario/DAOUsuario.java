package Usuario;

import org.hibernate.Session;

import Controlador.ConexionHB;
import Modelo.Usuario;

public class DAOUsuario {
	public void addNuevoUsuario(Usuario user) {
		Session session = ConexionHB.iniciarConexion().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		ConexionHB.cerrarConexion();
	}
}
