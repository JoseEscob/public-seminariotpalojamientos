package exceptions;

public class CargaViewException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String messageError = "";
	
	public CargaViewException() {
		this.messageError = "CargaViewException: Ocurrió un error al cargar los elementos de la vista";
	}
	public CargaViewException(String messageError) {
		this.messageError = messageError;
	}
	
	public String getMessage() {
		return this.messageError;
	}
}
