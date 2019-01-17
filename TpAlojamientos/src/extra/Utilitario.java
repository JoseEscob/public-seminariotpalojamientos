package extra;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import org.joda.time.DateTime;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.io.File;

/**
 * Funciona como clase para implementar funciones reutilizables
 */
public class Utilitario {
	/// *********************** OTROS ******************************///
	/**
	 * Verifica que la ruta del parametro sea un archivo y no un directorio
	 * 
	 * @param path
	 * @return
	 */
	public static boolean existeElArchivo(String path) {
		File archivo = new File("/" + path);

		// if (archivo.exists() && !archivo.isDirectory())
		if (archivo.isFile())
			return true;
		else
			return false;
	}

	/// *********************** FECHAS ******************************///
	public static java.sql.Date textoAFechaSQL(String textoFecha) {
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.DDMMYYYY);
			long fechaSQL = ((formatter.parseDateTime(textoFecha)).toDateTime().getMillis());
			return new java.sql.Date(fechaSQL);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean esMayorDeEdad(String textoFecha) {
		try {
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

	public static String getCurrentDateString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.DDMMYYYY);
		DateTime currentDate = new DateTime(new java.util.Date());
		return formatter.print(currentDate);
	}

	public static String getCurrentDateAndHoursString() {
		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(Constantes.yyyyMMddHHmmss);
		java.util.Date date = new java.util.Date();
		return dateFormat.format(date); // 2016/11/16 12:08:43
	}

	public static java.sql.Date getCurrentDateAndHoursSQL() {
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

	public static Date getDateAndHoursFromString(String dateString) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		return sdf.parse(dateString);
	}

	public static int compareDateString(String dateString1, String dateString2) throws ParseException {
		Date date1 = getDateAndHoursFromString(dateString1);
		Date date2 = getDateAndHoursFromString(dateString2);

		if (date1.after(date2))
			return 1;
		if (date1.before(date2))
			return -1;
		if (date1.equals(date2))
			return 0;

		return 0;
	}

	/**
	 * Get a diff between two dates getDateDiff(date1,date2,TimeUnit.MINUTES);
	 * 
	 * @param java.util.Date
	 *            date1 the oldest date
	 * @param java.util.Date
	 *            date2 the newest date
	 * @param timeUnit
	 *            the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(java.util.Date date1, java.util.Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * 
	 * Map:{DAYS=1, HOURS=3, MINUTES=46, SECONDS=40, MILLISECONDS=0, MICROSECONDS=0,
	 * NANOSECONDS=0}
	 * 
	 * @see {@link https://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances}
	 */
	public static Map<TimeUnit, Long> computeDiff(Date date1, Date date2) {
		long diffInMillies = date2.getTime() - date1.getTime();
		List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);
		Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
		long milliesRest = diffInMillies;
		for (TimeUnit unit : units) {
			long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;
			result.put(unit, diff);
		}
		return result;
	}
}
