package extra;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import exceptions.FileHandlerException;

public class FileHandler {
	private static final int MemoryThreshold = 1024 * 1024 * 3;
	private static final int MaxFileSize = 1024 * 1024 * 10;
	private static final int MaxRequestSize = 1024 * 1024 * 20;
	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
	private List<FileItem> formItems;
	private HttpServletRequest request;
	
	public FileHandler(HttpServletRequest request) throws Exception {
		this.request = request;
		this.factory = new DiskFileItemFactory();
		this.factory.setSizeThreshold(MemoryThreshold);
		this.factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		this.upload = new ServletFileUpload(this.factory);
		this.upload.setFileSizeMax(MaxFileSize);
		this.upload.setSizeMax(MaxRequestSize);
		
	
		if(!this.upload.isMultipartContent(this.request)) {
			throw new FileHandlerException("El componente Servlet o formulario de datos no es MULTIPART.");
		}
		
		this.formItems = this.upload.parseRequest(this.request);
		
		if(this.formItems == null) {
			throw new FileHandlerException("Formulario de entrada con valor nulo.");
		}
		
		if(this.formItems.size() == 0) {
			throw new FileHandlerException("No se encontraron items en el formulario de entrada.");
		}
		
	}
	
	
	public List<FileItem> getFormItems(){
		return this.formItems;
	}

}
















