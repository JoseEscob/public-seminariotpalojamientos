package modelo.embebido;

import java.sql.Date;

import modelo.Usuario;

public class BajaEmb {
	private int idBaja;
	private Usuario usuario;
	private PublicacionEmb publicacion;
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public PublicacionEmb getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(PublicacionEmb publicacion) {
		this.publicacion = publicacion;
	}

	
	
}
