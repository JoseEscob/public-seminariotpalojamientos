package extra;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 * Funciona como clase para implementar métodos reutilizables
 */
public class Utilitario {

	public static Date textoAFecha(String textoFecha) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DDMMYYYY);
			// Date fecha = (Date) formatter.format('22-12-2000');

			// Date fecha = Date.valueOf(formatter.format(textoFecha));

			java.util.Date fechaTest = formatter.parse(textoFecha);
			Date fecha = (Date) fechaTest;
			return fecha;
		} catch (Exception e) {

			return null;
		}
	}

	public static boolean esMayorDeEdad(String textoFecha) {
		try {
			// String textoFecha = new
			// SimpleDateFormat(Constantes.DDMMYYYY).format(fechaNac);//Date fechaNac
			LocalDate birthdate = LocalDate.parse(textoFecha);
			LocalDate now = new LocalDate();
			int edad = Years.yearsBetween(birthdate, now).getYears();
			if (edad >= 18)
				return true;
			else
				return false;
		} catch (Exception e) {
			throw e;
		}
	}
}
