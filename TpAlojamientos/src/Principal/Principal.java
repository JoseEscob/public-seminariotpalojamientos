package Principal;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import controladoresDAO.Usuarios;
import modelo.Usuario;
import com.google.gson.*;

public class Principal {

	

	public static void main(String[] args) {
		/*
		 * //Las siguientes tres lineas es lo que hay que hacer para convertir el tipo
		 * de dato java.utl.Date a java.sql.Date Calendar calendario =
		 * Calendar.getInstance(); java.util.Date date = calendario.getTime();
		 * java.sql.Date d = new java.sql.Date(date.getTime());
		 */

		
		
		 String json1 = "[{\"dorsal\":6," + "\"name\":\"Iniesta\","
	                + "\"demarcation\":[\"Right winger\",\"Midfielder\"],"
	                + "\"team\":\"FC Barcelona\"}]";

	        JsonParser parser = new JsonParser();

	        // Obtain Array
	        JsonArray gsonArr = parser.parse(json1).getAsJsonArray();

	        // for each element of array
	        for (JsonElement obj : gsonArr) {

	            // Object of array
	            JsonObject gsonObj = obj.getAsJsonObject();

	            // Primitives elements of object
	            int dorsal = gsonObj.get("dorsal").getAsInt();
	            String name = gsonObj.get("name").getAsString();
	            String team = gsonObj.get("team").getAsString();

	            // List of primitive elements
	            JsonArray demarcation = gsonObj.get("demarcation").getAsJsonArray();
	            List listDemarcation = new ArrayList();
	            for (JsonElement demarc : demarcation) {
	                listDemarcation.add(demarc.getAsString());
	            }

	            System.out.println(gsonObj.toString());
		
	        }
		
		System.out.println("done!");

	}

	public static java.sql.Date nuevaFecha(String s) {

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

}
