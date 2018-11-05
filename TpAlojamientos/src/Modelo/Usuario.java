package Modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@Column(name = "idUsuario", nullable = false)
	private int idUsuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "dni")
	private String dni;

	@Column(name = "mail")
	private String mail;

	@Column(name = "fechaNac")
	private Date fechaNac;

	@Column(name = "nroUsuario")
	private String nroUsuario;

	@Column(name = "claveUsuario")
	private String claveUsuario;

	@Column(name = "puntaje")
	private float puntaje;

	@Column(name = "idUsuario")
	private int estadoUsuario;
}
