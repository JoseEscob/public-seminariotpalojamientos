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
		user.setDni("32123");
		user.setClaveUsuario("234");
		user.setNombre("nUsuaasdsdrio");
		user.setApellido("ggggdfgd");
		user.setFechaNac(d);
		user.setMail("rtghjk");
		user.setNroUsuario("1233");
		
		
		users.insert(user);
		
		for(Usuario s : users.getAll()) {
			System.out.println(s.getNombre());
		}
		
		user.setIdUsuario(1);
		user = users.get(user);
		System.out.println(user.getNombre());
		user.setNombre("David");
		users.update(user);
		
		user.setIdUsuario(users.getCount()-1);
		users.remove(user);
		
		/*DAOUsuario.addNuevoUsuario(user);
		DAOUsuario.readAllUsuario();
*/
	}

}
