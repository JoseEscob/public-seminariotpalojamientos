package modelo.embebido;


public class ComprobanteEmb {
	private int idComprobante;
	private SolicitudEmb solicitud;
	private String nombreAnfitrion;
	private String apellidoAnfitrion;
	private String nombreHuesped;
	private String apellidoHuesped;
	private String descripcionPublicacion;
	private int precioFinal;
	private boolean habilitado;
	public int getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
	}
	public String getNombreAnfitrion() {
		return nombreAnfitrion;
	}
	public void setNombreAnfitrion(String nombreAnfitrion) {
		this.nombreAnfitrion = nombreAnfitrion;
	}
	public String getApellidoAnfitrion() {
		return apellidoAnfitrion;
	}
	public void setApellidoAnfitrion(String apellidoAnfitrion) {
		this.apellidoAnfitrion = apellidoAnfitrion;
	}
	public String getNombreHuesped() {
		return nombreHuesped;
	}
	public void setNombreHuesped(String nombreHuesped) {
		this.nombreHuesped = nombreHuesped;
	}
	public String getApellidoHuesped() {
		return apellidoHuesped;
	}
	public void setApellidoHuesped(String apellidoHuesped) {
		this.apellidoHuesped = apellidoHuesped;
	}
	public String getDescripcionPublicacion() {
		return descripcionPublicacion;
	}
	public void setDescripcionPublicacion(String descripcionPublicacion) {
		this.descripcionPublicacion = descripcionPublicacion;
	}
	public int getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(int precioFinal) {
		this.precioFinal = precioFinal;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public SolicitudEmb getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(SolicitudEmb solicitud) {
		this.solicitud = solicitud;
	}

}
