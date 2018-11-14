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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNroUsuario() {
		return nroUsuario;
	}

	public void setNroUsuario(String nroUsuario) {
		this.nroUsuario = nroUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}

	public int getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(int estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", publicaciones=" + publicaciones + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", dni=" + dni + ", mail=" + mail + ", fechaNac=" + fechaNac
				+ ", nroUsuario=" + nroUsuario + ", claveUsuario=" + claveUsuario + ", puntaje=" + puntaje
				+ ", estadoUsuario=" + estadoUsuario + "]";
	}

}
