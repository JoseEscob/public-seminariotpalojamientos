package exceptions;

public class ValidacionException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String messageError = "";

	public ValidacionException() {
		this.messageError = "ValidacionException: Ocurri� un error al validar";
	}

	public ValidacionException(String messageError) {
		this.messageError = messageError;
	}

	public String getMessage() {
		return this.messageError;
	}
}
