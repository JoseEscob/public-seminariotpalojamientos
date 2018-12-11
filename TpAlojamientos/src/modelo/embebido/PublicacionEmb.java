package modelo.embebido;

import java.sql.Date;

import modelo.Usuario;
import modelo.TipoAlojamiento;

public class PublicacionEmb {

	private int idPublicacion;
	private Usuario usuario;
	private TipoAlojamiento tipoAlojamiento;
	private String nombre;
	private String descripcion;
	private String domicilio;
	private LocalidadEmb localidad;
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
	
	public int getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
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
	public float getPrecioNoche() {
		return precioNoche;
	}
	public void setPrecioNoche(float precioNoche) {
		this.precioNoche = precioNoche;
	}
	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}
	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
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
	public boolean isBitJardin() {
		return bitJardin;
	}
	public void setBitJardin(boolean bitJardin) {
		this.bitJardin = bitJardin;
	}
	public boolean isBitCochera() {
		return bitCochera;
	}
	public void setBitCochera(boolean bitCochera) {
		this.bitCochera = bitCochera;
	}
	public boolean isBitMascotas() {
		return bitMascotas;
	}
	public void setBitMascotas(boolean bitMascotas) {
		this.bitMascotas = bitMascotas;
	}
	public boolean isBitFumadores() {
		return bitFumadores;
	}
	public void setBitFumadores(boolean bitFumadores) {
		this.bitFumadores = bitFumadores;
	}
	public boolean isBitAmoblada() {
		return bitAmoblada;
	}
	public void setBitAmoblada(boolean bitAmoblada) {
		this.bitAmoblada = bitAmoblada;
	}
	public boolean isBitDesayuno() {
		return bitDesayuno;
	}
	public void setBitDesayuno(boolean bitDesayuno) {
		this.bitDesayuno = bitDesayuno;
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
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public TipoAlojamiento getTipoAlojamiento() {
		return tipoAlojamiento;
	}
	public void setTipoAlojamiento(TipoAlojamiento tipoAlojamiento) {
		this.tipoAlojamiento = tipoAlojamiento;
	}
	public LocalidadEmb getLocalidad() {
		return localidad;
	}
	public void setLocalidad(LocalidadEmb localidad) {
		this.localidad = localidad;
	}

	
	
	
}
