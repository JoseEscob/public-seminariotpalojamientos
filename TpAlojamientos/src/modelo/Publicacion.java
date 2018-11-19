package modelo;

import java.util.Date;

public class Publicacion {
	private int idPublicacion;
	private int idUsuario;
	private int idTipoAlojamiento;
	private String nombre;
	private String descripcion;
	private int idLocalidad;
	private int codPostal;
	private String coordenadas;
	private float precioNoche;

	private int metrosCuadrados;
	private int cantPersonas;
	private int cantAmbientes;
	private int cantBanios;
	private int cantHabitaciones;

	private boolean bitJardin;
	private boolean bitCochera;
	private boolean bitMascotas;
	private boolean bitFumadores;
	private boolean bitAmoblada;
	private boolean bitDesayuno;

	private Date fechaAlta;
	private float puntaje;
	private boolean habilitado;

}
