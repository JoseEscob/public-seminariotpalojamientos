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

import javax.imageio.ImageIO;

// https://memorynotfound.com/java-resize-image-fixed-width-height-example/
public class ManejoDeArchivos {
	private static final String UploadDirectory = "ruta";

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