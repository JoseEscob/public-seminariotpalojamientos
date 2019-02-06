package extra;

public class InfoMessage {
	private String message;
	private boolean estado;

	public InfoMessage() {

	}

	public InfoMessage(boolean estado, String message) {
		this.message = message;
		this.estado = estado;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
