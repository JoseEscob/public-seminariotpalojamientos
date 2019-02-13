package modelo;

import java.sql.Date;

public class PublicacionReservada {
	private int idPublicacion;
	private boolean estaReservada;
	private Date fechaReservaInicio;
	private Date fechaReservaFin;

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public boolean isEstaReservada() {
		return estaReservada;
	}

	public void setEstaReservada(boolean estaReservada) {
		this.estaReservada = estaReservada;
	}

	public Date getFechaReservaInicio() {
		return fechaReservaInicio;
	}

	public void setFechaReservaInicio(Date fechaReservaInicio) {
		this.fechaReservaInicio = fechaReservaInicio;
	}

	public Date getFechaReservaFin() {
		return fechaReservaFin;
	}

	public void setFechaReservaFin(Date fechaReservaFin) {
		this.fechaReservaFin = fechaReservaFin;
	}
}
