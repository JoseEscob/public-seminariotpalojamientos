package Principal;

import java.util.Calendar;

import controladoresDAO.Usuarios;
import modelo.Usuario;

public class Principal {

	public static void main(String[] args) {
		//Las siguientes tres lineas es lo que hay que hacer para convertir el tipo de dato java.utl.Date a java.sql.Date
		Calendar calendario = Calendar.getInstance();
		java.util.Date date = calendario.getTime();
		java.sql.Date d = new java.sql.Date(date.getTime());

		
		Usuarios users = new Usuarios();
		Usuario user = new Usuario();
		user.setDni("11111");
		user.setClaveUsuario("123");
		user.setNombre("nUsuario");
		user.setApellido("aUsuario");
		user.setFechaNac(d);
		user.setMail("mail");
		user.setNroUsuario("123");
		
		
		users.insert(user);
		
		for(Usuario s : users.getAll()) {
			System.out.println(s.getNombre());
		}
		/*DAOUsuario.addNuevoUsuario(user);
		DAOUsuario.readAllUsuario();
*/
	}

}
