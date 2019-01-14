package exceptions;

public class ServidorException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String messageError = "";

	public ServidorException() {
		this.messageError = "ServidorException: Ocurrió un error al validar";
	}

	public ServidorException(String messageError) {
		this.messageError = messageError;
	}

	public String getMessage() {
		return this.messageError;
	}
}
