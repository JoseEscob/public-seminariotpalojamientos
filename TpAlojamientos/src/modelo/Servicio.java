package modelo;

public class Servicio {

	private int idPublicacion;
	private int idServicio;
	private TipoServicio objTipoServicio;

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public TipoServicio getObjTipoServicio() {
		return objTipoServicio;
	}

	public void setObjTipoServicio(TipoServicio objTipoServicio) {
		this.objTipoServicio = objTipoServicio;
	}
}
