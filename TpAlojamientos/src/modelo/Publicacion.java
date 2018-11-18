package modelo;

import java.util.Date;

public class Publicacion {
	private int id_publicacion;
	private int id_usuario;
	private int tipo_alojamiento;
	private String nombre;
	private String descripcion;
	private int id_localidad;
	private int codigo_postal;
	private String coordenadas;
	private float precio_noche;

	private int metros_cuadrados;
	private int cantPersonas;
	private int cantAmbientes;
	private int cantBanios;
	private int cantHabitaciones;

	private boolean bit_jardin;
	private boolean bit_cochera;
	private boolean bit_mascotas;
	private boolean bit_fumadores;
	private boolean bit_amoblada;
	private boolean bit_desayuno;

	private Date fecha_alta;
	private float puntaje;
	private boolean habilitado;

}
