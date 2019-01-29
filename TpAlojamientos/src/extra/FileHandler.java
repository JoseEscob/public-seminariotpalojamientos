package extra;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import exceptions.FileHandlerException;

public class FileHandler {
	private static final int MemoryThreshold = 1024 * 1024 * 3;
	private static final int MaxFileSize = 1024 * 1024 * 10;
	private static final int MaxRequestSize = 1024 * 1024 * 20;
	private HttpServletRequest request;
	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
	private List<FileItem> formItems;
	private String uploadPath;
	
	public FileHandler(HttpServletRequest request, String uploadPath) {
		this.request = request;
		this.uploadPath = uploadPath;
		this.factory = new DiskFileItemFactory();
		this.factory.setSizeThreshold(MemoryThreshold);
		this.factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		this.upload = new ServletFileUpload(this.factory);
		this.upload.setFileSizeMax(MaxFileSize);
		this.upload.setSizeMax(MaxRequestSize);
		
	}

	public int uploadFiles() throws Exception {
		File uploadDir = new File(this.uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		if(!this.upload.isMultipartContent(this.request)) {
			throw new FileHandlerException("El componente Servlet o formulario de datos no es MULTIPART.");
		}
		
		this.formItems = this.upload.parseRequest(this.request);
		
		if(formItems == null) {
			throw new FileHandlerException("Formulario de entrada con valor nulo.");
		}
		
		if(formItems.size() == 0) {
			throw new FileHandlerException("No se encontraron items en el formulario de entrada.");
		}
		
		
		//Se guardan los archivos
		int count = 0;
		
		for(FileItem item : this.formItems) {
			if(item.isFormField()) {
				//form fields 
			}
			else {
				count ++;
				String fileName = new File(item.getName()).getName();
				
				String newName = count + "." + FilenameUtils.getExtension(fileName);
				String filePath = this.uploadPath + File.separator + newName;
				
				File storeFile = new File(filePath);
				System.out.println(storeFile.getPath());
				item.write(storeFile);
			}
		}
		
		
		return count;
	}
	
	public List<FileItem> getFormItems(){
		return this.formItems;
	}
	public static String getUploadPath(ServletContext servletContext, String uploadFolderName) {
		return servletContext.getRealPath("")+File.separator+uploadFolderName;
	}
	public static String getPathForDB(File file) {
		return file.getParentFile().getName() + File.separator + file.getName();
	}
	
	public static File[] getFiles(String path) {
		return new File(path).listFiles();
	}
	
}
















