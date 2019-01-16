package modelo;

import java.sql.Date;

public class Usuario {
	private int idUsuario;
	private String nombre;
	private String apellido;
	private String dni;
	private String mail;
	private Date fechaNac;
	private String usuario;
	private String clave;
	private boolean sexo;
	private String rutaFotoPerfil;
	private boolean admin;
	private float puntaje;
	private boolean habilitado;
	private String fechaAlta;
	// private Date fechaUltConexion;
	private String fechaUltConexion;
	private boolean verificado;

	// private Date anteriorFechaUltConexion;
	private String anteriorFechaUltConexion;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public String getRutaFotoPerfil() {
		return rutaFotoPerfil;
	}

	public void setRutaFotoPerfil(String rutaFotoPerfil) {
		this.rutaFotoPerfil = rutaFotoPerfil;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaUltConexion() {
		return fechaUltConexion;
	}

	public void setFechaUltConexion(String fechaUltConexion) {
		this.fechaUltConexion = fechaUltConexion;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public String getAnteriorFechaUltConexion() {
		return anteriorFechaUltConexion;
	}

	public void setAnteriorFechaUltConexion(String anteriorFechaUltConexion) {
		this.anteriorFechaUltConexion = anteriorFechaUltConexion;
	}

}
