package exceptions;

public class FileHandlerException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String messageError = "";
	
	public FileHandlerException() {
		this.messageError = "FileHandlerException: Ocurrió un error al iniciar los componentes.";
	}
	public FileHandlerException(String messageError) {
		this.messageError = messageError;
	}
	
	public String getMessage() {
		return this.messageError;
	}
}

