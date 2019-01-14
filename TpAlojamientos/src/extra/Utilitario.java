package extra;

import org.joda.time.DateTime;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Funciona como clase para implementar funciones reutilizables
 */
public class Utilitario {

	public static java.sql.Date textoAFechaSQL(String textoFecha) {
		try {
			// SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DDMMYYYY);
			// Date fecha = (Date) formatter.format('22-12-2000');

			// Date fecha = Date.valueOf(formatter.format(textoFecha));
			// java.util.Date fechaTest = formatter.parse(textoFecha);
			DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.DDMMYYYY);
			long fechaSQL = ((formatter.parseDateTime(textoFecha)).toDateTime().getMillis());
			return new java.sql.Date(fechaSQL);
		} catch (Exception e) {

			return null;
		}
	}

	public static boolean esMayorDeEdad(String textoFecha) {
		try {
			// String textoFecha = new
			// SimpleDateFormat(Constantes.DDMMYYYY).format(fechaNac);//Date fechaNac
			// DateTime.parse(strDate,
			// DateTimeFormat.forPattern(format).withLocale(Locale.US)).toDate();

			LocalDate birthdate = LocalDate.parse(textoFecha, DateTimeFormat.forPattern(Constantes.DDMMYYYY));
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

	public static String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.DDMMYYYY);
		DateTime currentDate = new DateTime(new java.util.Date());
		return formatter.print(currentDate);
	}

	public static String getCurrentDateAndHoursString() {
		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(Constantes.yyyyMMddHHmmss);
		java.util.Date date = new java.util.Date();
		return dateFormat.format(date); // 2016/11/16 12:08:43
	}
	
	public static java.sql.Date getCurrentDateAndHours() {
		try {
			java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(Constantes.yyyyMMddHHmmss);
			java.util.Date date = new java.util.Date();
			String textoFecha = dateFormat.format(date);
			DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.yyyyMMddHHmmss);
			long fechaSQL = ((formatter.parseDateTime(textoFecha)).toDateTime().getMillis());
			return new java.sql.Date(fechaSQL);
		} catch (Exception e) {
			return null;
		}
	}
}
