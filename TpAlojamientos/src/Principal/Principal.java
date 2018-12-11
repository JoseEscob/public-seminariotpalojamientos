package Principal;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import controladoresDAO.Usuarios;
import modelo.Usuario;

public class Principal {

	static String[] nombres = {"David", "Tamara", "Jose", "Roberto", "Josefina", "Ana", "Ruperto", "Alberto", "Mario"};
	static String[] apellidos = {"Martinez", "Pumares", "Escobar", "James", "Gomez", "Castelar", "Carba", "Lopez", "Ponce de Leon"};
	static String[] documentos = {"39846356", "35468478", "21456789", "36987653", "23567897", "54321654", "39846345", "12345678", "14568978"};
	static String[] usuarios = {"miko","josipe","atrameo","juko","trepber","luzet","dazert","zerak","jako"};
	static String[] mails = {"miko@gmail.com","josipe@gmail.com","atrameo@gmail.com","juko@gmail.com","trepber@gmail.com","luzet@gmail.com","dazert@gmail.com","zerak@gmail.com","jako@gmail.com"};
	
	public static void main(String[] args) {

		/*
		//Las siguientes tres lineas es lo que hay que hacer para convertir el tipo de dato java.utl.Date a java.sql.Date
		Calendar calendario = Calendar.getInstance();
		java.util.Date date = calendario.getTime();
		java.sql.Date d = new java.sql.Date(date.getTime());
*/
		
		
		// Lote de datos de prueba
		//variables
		Usuarios usuarios = new Usuarios();
		
		//usuarios
	/*	Usuario usuario = new Usuario();
		usuario.setNombre("David");
		usuario.setApellido("Martinez");
		usuario.setDni("39846388");
		usuario.setMail("david@gmail.com");
		usuario.setFechaNac(nuevaFecha("30/08/1996"));
		usuario.setUsuario("jekombrecka");
		usuario.setClaveUsuario("rader");
		usuario.setPuntaje(5);
		usuario.setSexo(true);
		usuario.setAdmin(true);
		*/
		for(Usuario a : newUsers())
			usuarios.insert();
		
		System.out.println("done!");

		
	}
	public static java.sql.Date nuevaFecha(String s){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = null;
		try {
			date = dateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new java.sql.Date(date.getTime());
	}

	public static ArrayList<Usuario> newUsers() {
		ArrayList<Usuario> list = new ArrayList<Usuario>();
		
		for(int i = 0; i < 9; i++) {
			Usuario usuario = new Usuario();
			usuario.setNombre(nombres[i]);
			usuario.setApellido(apellidos[i]);
			usuario.setDni(documentos[i]);
			
			usuario.setFechaNac(nuevaFecha("30/08/1996"));
			usuario.setClaveUsuario("clave");
			usuario.setMail(mails[i]);
			usuario.setUsuario(usuarios[i]);
			usuario.setPuntaje(i);
			usuario.setSexo(true);
			usuario.setAdmin(false);
			list.add(usuario);
	
		}		
	
		return list;
	}

	

}
