package modelo;

import java.sql.Date;

public class Publicacion {
	private int idPublicacion;
	private int idUsuario;
	private int idTipoAlojamiento;
	private String nombre;
	private String descripcion;

	private int idLocalidad;
	private int codPostal;
	private String coordenadas;
	private String calle;
	private int altura;
	private int piso;
	private String dpto;

	private int supCubierta;
	private int supDescubierta;
	private int chkExpensas;
	private int precioExpensas;
	private int precioNoche;

	private int cantPersonas;
	private int cantAmbientes;
	private int cantBanios;
	private int cantHabitaciones;

	private Date fechaAlta;
	private float puntaje;
	private boolean habilitado;

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTipoAlojamiento() {
		return idTipoAlojamiento;
	}

	public void setIdTipoAlojamiento(int idTipoAlojamiento) {
		this.idTipoAlojamiento = idTipoAlojamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public int getSupCubierta() {
		return supCubierta;
	}

	public void setSupCubierta(int supCubierta) {
		this.supCubierta = supCubierta;
	}

	public int getSupDescubierta() {
		return supDescubierta;
	}

	public void setSupDescubierta(int supDescubierta) {
		this.supDescubierta = supDescubierta;
	}

	public int getChkExpensas() {
		return chkExpensas;
	}

	public void setChkExpensas(int chkExpensas) {
		this.chkExpensas = chkExpensas;
	}

	public int getPrecioExpensas() {
		return precioExpensas;
	}

	public void setPrecioExpensas(int precioExpensas) {
		this.precioExpensas = precioExpensas;
	}

	public int getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(int precioNoche) {
		this.precioNoche = precioNoche;
	}

	public int getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public int getCantAmbientes() {
		return cantAmbientes;
	}

	public void setCantAmbientes(int cantAmbientes) {
		this.cantAmbientes = cantAmbientes;
	}

	public int getCantBanios() {
		return cantBanios;
	}

	public void setCantBanios(int cantBanios) {
		this.cantBanios = cantBanios;
	}

	public int getCantHabitaciones() {
		return cantHabitaciones;
	}

	public void setCantHabitaciones(int cantHabitaciones) {
		this.cantHabitaciones = cantHabitaciones;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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

}
