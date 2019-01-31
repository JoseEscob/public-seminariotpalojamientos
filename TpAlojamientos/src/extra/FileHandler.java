package extra;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.imageio.ImageIO;
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

		if (this.formItems == null) {
			throw new FileHandlerException("Formulario de entrada con valor nulo.");
		}

		if (this.formItems.size() == 0) {
			throw new FileHandlerException("No se encontraron items en el formulario de entrada.");
		}

	}

	public List<FileItem> getFormItems() {
		return this.formItems;
	}


}

	/***********************************/
	public void runTestResize() {
		String rutaOriginal = "Desktop\\originalFile.jpg";
		String rutaResized = "Desktop\\resizedFile.jpg";
		File jpgOriginalFile = new File(rutaOriginal);
		File jpgResizedFile = new File(rutaResized);

		resizeImage(jpgOriginalFile, jpgResizedFile, 200, 200, "png");
	}

	public static void resizeImage(File originalFile, File resizedFile, int width, int height, String format) {
		try {
			BufferedImage original = ImageIO.read(originalFile);
			BufferedImage resized = new BufferedImage(width, height, original.getType()); // new
																							// BufferedImage(SMALL_SIZE,
																							// SMALL_SIZE,
																							// BufferedImage.TYPE_INT_RGB);
			Graphics2D graph = resized.createGraphics();
			graph.drawImage(original, 0, 0, width, height, null);
			graph.dispose();
			ImageIO.write(resized, format, resizedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copiarUnArchivo(String rutaOrigen, String rutaDestino) {
		// Path from = cfgFilePath.toPath(); // convert from File to Path
		// Path to = Paths.get(strTarget); // convert from String to Path
		// Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
		//

		// These files do not exist in our example.
		FileSystem system = FileSystems.getDefault();
		Path original = system.getPath(rutaOrigen);// ("C:\\programs\\mystery.txt");
		Path target = system.getPath(rutaDestino);// ("C:\\programs\\mystery-2.txt");

		try {
			// Throws an exception if the original file is not found.
			Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			System.out.println("ERROR");
		}

	}

	private static final String UploadDirectory = "ruta";

	public static boolean copiarVariosArchivos(String rutaOrigen, String projectName, String nombreNuevoArchivo) {
		boolean estadoCopia = false;

		String rutaDestino = new File(System.getProperty("user.home")).getPath() + File.separator + "git"
				+ File.separator + "seminariotpalojamientos" + projectName + File.separator + UploadDirectory;

		File rutaDestinoDirectorio = new File(rutaDestino);
		if (!rutaDestinoDirectorio.exists()) {
			rutaDestinoDirectorio.mkdir();
		}

		String filePath = rutaDestino + File.separator + nombreNuevoArchivo;
		File storeFile = new File(filePath);
		// storeFile.

		return estadoCopia;
	}

}
