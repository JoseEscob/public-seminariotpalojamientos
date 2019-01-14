package extra;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// https://memorynotfound.com/java-resize-image-fixed-width-height-example/
public class ImageResize {
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
}