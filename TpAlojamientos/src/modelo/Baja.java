package modelo;

import java.sql.Date;

public class Baja {
	private int idBaja;
	private int idUsuario;
	private int idPublicacion;
	private Date fechaBaja;
	private Date fechaSolucion;
	private boolean solucionado;
	private String razon_baja;
	private boolean habilitado;
	public int getIdBaja() {
		return idBaja;
	}
	public void setIdBaja(int idBaja) {
		this.idBaja = idBaja;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Date getFechaSolucion() {
		return fechaSolucion;
	}
	public void setFechaSolucion(Date fechaSolucion) {
		this.fechaSolucion = fechaSolucion;
	}
	public boolean isSolucionado() {
		return solucionado;
	}
	public void setSolucionado(boolean solucionado) {
		this.solucionado = solucionado;
	}
	public String getRazon_baja() {
		return razon_baja;
	}
	public void setRazon_baja(String razon_baja) {
		this.razon_baja = razon_baja;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	
	
}
