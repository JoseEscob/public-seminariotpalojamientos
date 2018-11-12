package Principal;

import Modelo.Usuario;
import Usuario.DAOUsuario;

public class Principal {

	public static void main(String[] args) {
		Usuario user = new Usuario();
		user.setDni("11111");
		user.setClaveUsuario("123");
		user.setNombre("nUsuario");
		user.setApellido("aUsuario");

		DAOUsuario.addNuevoUsuario(user);
		DAOUsuario.readAllUsuario();

	}

}
