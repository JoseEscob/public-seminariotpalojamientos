package controllersServlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;

import extra.Constantes;
import extra.FileHandler;
import modelo.Imagen;
import controladoresDAO.Imagenes;



		
/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
@MultipartConfig
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileHandler fileHandler;
	private Imagenes imagenDao = new Imagenes();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet - TestServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost - TestServlet");
		try {
			
			fileHandler = new FileHandler(request);

			switch(fileHandler.getParameter(Constantes.accionPOST)) {
				case "retornaImagenes":
					retornaImagenes(request, response);
					break;
				case "saveTempFiles":
					saveTempFiles(request, response);
					break;
				default: break;
			}

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void retornaImagenes(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String temporal = getServletContext().getRealPath("")+Constantes.RUTATemporal;
		FileHandler.MakeDir(temporal);
		File temporalFolder = new File(temporal);
		
		
		int contador = temporalFolder.listFiles().length;
		ArrayList<Imagen> listImagenes = new ArrayList<Imagen>();
		for(FileItem item : fileHandler.getFiles()) {
			contador++;
			if(contador <= 20) {
				//Guardamos el archivo en el servidor
				//File storeFile = new File(temporal+File.separator+contador+"."+FilenameUtils.getExtension(new File(item.getName()).getName()));
				File storeFile = new File(temporal+File.separator+item.getName());
				File delete = FileHandler.IfExists(temporal, storeFile.getName());
				if(delete != null) {
					delete.delete();
				}
				item.write(storeFile);
				//Ahora damos el alta en la base de datos
				Imagen imagen = new Imagen();
				imagen.setIdImagen(contador);
				imagen.setHabilitado(true);
				imagen.setRutaImgPublicacion(Constantes.RUTATemporal+ File.separator + storeFile.getName());
				listImagenes.add(imagen);
			}else {
				resultMap.put("limite", "limite");
				
			}
		}
		resultMap.put("cantidadSubida", temporalFolder.listFiles().length);
		
		if(fileHandler.existsParameterVaule("readyToDelete")) {
			FileHandler.DeleteAllFiles(temporal);
		}
		
		resultMap.put("imagenes", listImagenes);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(new Gson().toJson(resultMap)); // <----- AJAX RESPONDE SIN REDIRIGIR
	}
	
	private void saveTempFiles(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String idPublicacionString = fileHandler.getParameter("idPublicacion");
		if(idPublicacionString != null) {
			int idPublicacion = Integer.parseInt(idPublicacionString);
			String temporal = getServletContext().getRealPath("")+Constantes.RUTATemporal;
			String newPath = getServletContext().getRealPath("")+Constantes.RUTACarpetaFotosPublicacion+idPublicacion;
			
			FileHandler.MakeDir(newPath);
			File temporalFolder = new File(temporal);
			
			
			ArrayList<Imagen> listaImagenesdb = imagenDao.getAllByIdPublicacion(idPublicacion);
			for(Imagen i : listaImagenesdb) {
				imagenDao.remove(i);
			}
			
			
			
			ArrayList<Imagen> listImagenes = new ArrayList<Imagen>();
			
			if(temporalFolder.isDirectory()) {
				int contador = 0;
				for(File file : temporalFolder.listFiles()) {
					contador ++;
					
					String extension = FilenameUtils.getExtension(new File(file.getName()).getName());
					File storeFile = new File(newPath+File.separator+contador+"."+extension);
					
					File delete = FileHandler.IfExists(newPath, storeFile.getName());
					if(delete != null)
						delete.delete();
					
					file.renameTo(storeFile);
					
					
					
					
					Imagen imagen = new Imagen();
					imagen.setIdImagen(contador);
					imagen.setHabilitado(true);
					imagen.setIdPublicacion(idPublicacion);
					imagen.setRutaImgPublicacion(new File(Constantes.RUTACarpetaFotosPublicacion+idPublicacion+File.separator+storeFile.getName()).getPath());
					//imagenDao.insert(imagen);
					listImagenes.add(imagen);
				}
			
				
				FileHandler.DeleteAllFiles(temporal);
				request.setAttribute("imagenes", listImagenes);
				request.getRequestDispatcher("/InicioAdmin.jsp").forward(request, response);
				
			}
			
			
		}
	}
	
	
	
}

