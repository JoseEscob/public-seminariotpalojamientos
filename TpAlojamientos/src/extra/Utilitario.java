package extra;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Funciona como clase para métodos y Constantes reutilizables
 */
public class Utilitario {

	public static Date textoAFecha(String textoFecha) {
		try {
			// textoFecha = textoFecha.replace("/", "-");
			SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DDMMYYYY);
			// Date fecha = (Date) formatter.format('22-12-2000');

			// Date fecha = Date.valueOf(formatter.format(textoFecha));

			java.util.Date fechaTest = new SimpleDateFormat("dd/MM/yyyy").parse("20/12/2011");
			Date fecha = (Date) fechaTest;
			return fecha;
		} catch (Exception e) {

			return null;
		}
	}
}
