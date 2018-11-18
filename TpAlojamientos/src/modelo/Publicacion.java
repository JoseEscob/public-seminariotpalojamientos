package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name = "Publicacion")
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_publicacion", nullable = false)
	private int id_publicacion;

	@Column(name = "id_usuario", nullable = false)
	private int id_usuario;

	@Column(name = "tipo_alojamiento", nullable = false)
	private int tipo_alojamiento;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "id_localidad")
	private int id_localidad;

	@Column(name = "codigo_postal")
	private int codigo_postal;

	@Column(name = "coordenadas")
	private String coordenadas;

	@Column(name = "precio_noche")
	private float precio_noche;

	@Column(name = "metros_cuadrados")
	private int metros_cuadrados;
	@Column(name = "cantPersonas")
	private int cantPersonas;
	@Column(name = "cantAmbientes")
	private int cantAmbientes;
	@Column(name = "cantBanios")
	private int cantBanios;
	@Column(name = "cantHabitaciones")
	private int cantHabitaciones;

	@Column(name = "bit_jardin")
	private boolean bit_jardin;
	@Column(name = "bit_cochera")
	private boolean bit_cochera;
	@Column(name = "bit_mascotas")
	private boolean bit_mascotas;
	@Column(name = "bit_fumadores")
	private boolean bit_fumadores;
	@Column(name = "bit_amoblada")
	private boolean bit_amoblada;
	@Column(name = "bit_desayuno")
	private boolean bit_desayuno;

	@Column(name = "fecha_alta")
	private Date fecha_alta;

	@Column(name = "puntaje")
	private float puntaje;

	@Column(name = "habilitado")
	private boolean habilitado;

}
