package exceptions;

public class LectorDatosException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String messageError = "";

	public LectorDatosException() {
		this.messageError = "LectorDatosException: Ocurrió un error al leer los datos";
	}

	public LectorDatosException(String messageError) {
		this.messageError = messageError;
	}

	public String getMessage() {
		return this.messageError;
	}
}
