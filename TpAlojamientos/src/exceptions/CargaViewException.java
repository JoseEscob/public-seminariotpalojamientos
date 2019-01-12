package exceptions;

public class CargaViewException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String messageError = "";
	
	public CargaViewException() {
		this.messageError = "Ecurrio un error al cargar los elementos de la vista";
	}
	public CargaViewException(String err) {
		this.messageError = err;
	}
	
	public String getMessage() {
		return this.messageError;
	}
}
