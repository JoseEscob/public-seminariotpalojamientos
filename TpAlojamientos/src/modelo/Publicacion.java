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
	private boolean chkExpensas;
	private int precioExpensas;
	private int precioNoche;
	private boolean chkPuedeVariarCantPersonas;
	
	private int cantPersonas;
	private int cantAmbientes;
	private int cantBanios;
	private int cantHabitaciones;
	private int aniosAntiguedad;

	private Date fechaAlta;
	private Date fechaUltModificado;
	private float puntaje;
	private boolean habilitado;
	private boolean verificado;

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

	public boolean getChkExpensas() {
		return chkExpensas;
	}

	public void setChkExpensas(boolean chkExpensas) {
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

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public int getAniosAntiguedad() {
		return aniosAntiguedad;
	}

	public void setAniosAntiguedad(int aniosAntiguedad) {
		this.aniosAntiguedad = aniosAntiguedad;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 	"\n idPublicacion["+this.idPublicacion+
				"]\n idUsuario["+this.idUsuario+
				"]\n idTipoAlojamiento["+this.idTipoAlojamiento+
				"]\n nombre["+this.nombre+
				"]\n descripcion["+this.descripcion+
				"]\n idLocalidad["+this.idLocalidad+
				"]\n codPostal["+this.codPostal+
				"]\n coordenadas["+this.coordenadas+
				"]\n calle["+this.calle+
				"]\n altura["+this.altura+
				"]\n piso["+this.piso+
				"]\n dpto["+this.dpto+
				"]\n supCubierta["+this.supCubierta+
				"]\n supDescubierta["+this.supDescubierta+
				"]\n chkExpensas["+this.chkExpensas+
				"]\n precioExpensas["+this.precioExpensas+
				"]\n precioNoche["+this.precioNoche+
				"]\n cantPersonas["+this.cantPersonas+
				"]\n cantAmbientes["+this.cantAmbientes+
				"]\n cantBanios["+this.cantBanios+
				"]\n cantHabitaciones["+this.cantHabitaciones+
				"]\n aniosAntiguedad["+this.aniosAntiguedad+
				"]\n fechaAlta["+this.fechaAlta+
				"]\n puntaje["+this.puntaje+
				"]\n habilitado["+ this.habilitado+
				"]\n verificado["+this.verificado+"]\n";
	}

	public boolean isChkPuedeVariarCantPersonas() {
		return chkPuedeVariarCantPersonas;
	}

	public void setChkPuedeVariarCantPersonas(boolean chkPuedeVariarCantPersonas) {
		this.chkPuedeVariarCantPersonas = chkPuedeVariarCantPersonas;
	}

	public Date getFechaUltModificado() {
		return fechaUltModificado;
	}

	public void setFechaUltModificado(Date fechaUltModificado) {
		this.fechaUltModificado = fechaUltModificado;
	}

}
