package Modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUsuario", nullable = false)
	private int idUsuario;

	// @ManyToOne(cascade = CascadeType.ALL, mappedBy="id_publicacion")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_publicacion")
	private List<Publicacion> publicaciones;

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

	@Column(name = "estadoUsuario")
	private int estadoUsuario;
}
