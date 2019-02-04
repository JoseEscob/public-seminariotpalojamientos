package controllersServlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import extra.ORSesion;
import controladoresDAO.Usuarios;
import controladoresDAO.Imagenes;
import controladoresDAO.Publicaciones;
import modelo.Usuario;
import modelo.Imagen;
import modelo.Publicacion;

/**
 * Servlet implementation class UploadFilesServlet
 */
@WebServlet("/UploadFilesServlet")
@MultipartConfig
public class UploadFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileHandler fileHandler;
	private String paginaJsp = "";
	private Usuarios usuarioDao = new Usuarios();
	private Imagenes imagenDao = new Imagenes();
	private Publicaciones publicacionDao = new Publicaciones();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet - UploadFilesServlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost - UploadFilesServlet");
		try {
			
			//Se inicializan los componentes primarios
			fileHandler = new FileHandler(request);

			switch(fileHandler.getParameter(Constantes.accionPOST)) {

				case "cambiarImagenUsuario":
					cambiarImagenUsuario(request, response);
					break;
				case "verImagenes":
					
					break;
				case "cargarImagen":
					cargarImagen(request, response);
					break;
				case "retornaImagenes":
					retornaImagenes(request, response);
					break;
				case "saveTempFiles":
					saveTempFiles(request, response);
					break;
				case "deleteImageTmp":
					deleteImageTmp(request, response);
					break;
				default: break;
			}

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getPathFotoUsuario(FileItem item, int idUsuario) {
		FileHandler.MakeDir(getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser + idUsuario);
		return getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser + idUsuario + File.separator + "fotoUsuario_"+idUsuario + "." + FilenameUtils.getExtension(new File(item.getName()).getName());

	}
	private String getPathFotosPublicaciones(FileItem item, int idPublicacion, int count) {
		FileHandler.MakeDir(getServletContext().getRealPath("") + Constantes.RUTACarpetaFotosPublicacion + idPublicacion);
		return getServletContext().getRealPath("") + Constantes.RUTACarpetaFotosPublicacion + idPublicacion + File.separator + count + "." + FilenameUtils.getExtension(new File(item.getName()).getName());
	}
	


	
	private void cargarImagen(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//	getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser

		int idUsuario = 3;
		FileItem item = fileHandler.getFiles().get(0);
		String filePath = getPathFotoUsuario(item, idUsuario);
		
		File storeFile = new File(filePath);
		File sFile = new File(Constantes.RUTAFolderFotoUser + idUsuario +File.separator+storeFile.getName());
		usuarioDao.updateRutaFotoPerfil(idUsuario, sFile.getPath());
		request.setAttribute("imagen", sFile.getPath());
		item.write(storeFile);
		
		paginaJsp ="/Test.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
	
	private void cambiarImagenUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	
		if(ORSesion.sesionActiva(request)) {
		
			Usuario objUsuario = ORSesion.getUsuarioBySession(request);
			int idUsuario = objUsuario.getIdUsuario();
			String rutaActual = usuarioDao.getRutaFotoPerfil_Usuario(idUsuario);
			
			//Se comprueba si hay una foto existente y si ese archivo aun está.
			File imagen = new File( getServletContext().getRealPath("") + rutaActual);
			if(imagen.exists()) {
				File carpeta = new File(imagen.getParent());
				if(carpeta.isDirectory()) {
					for(File file : carpeta.listFiles()) {
						file.delete();
					}
					FileItem item = fileHandler.getFiles().get(0);
					File storeFile = new File( getPathFotoUsuario(item, idUsuario));
					File sFile = new File(Constantes.RUTAFolderFotoUser + idUsuario +File.separator+storeFile.getName());
					System.out.println(storeFile.getPath());
					System.out.println(storeFile.getName());

					usuarioDao.updateRutaFotoPerfil(idUsuario, sFile.getPath());
					request.setAttribute("imagen", sFile.getPath());
					item.write(storeFile);
					objUsuario = usuarioDao.get(objUsuario);
					ORSesion.nuevaSesion(request, objUsuario);
				}
			}
			
			request.setAttribute("objUsuario", ORSesion.getUsuarioBySession(request));
			paginaJsp = "/UsuarioViewModif.jsp";
		}
		request.getRequestDispatcher(paginaJsp).forward(request, response);
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
		if(ORSesion.sesionActiva(request)) {
			int idUsuario = ORSesion.getUsuarioBySession(request).getIdUsuario();	
			ArrayList<Publicacion> listPublicacionesUsuario = publicacionDao.getAllByIdUsuario(idUsuario);
			Publicacion ultimaPublicacion = listPublicacionesUsuario.get(listPublicacionesUsuario.size()-1);
			
			
			if(ultimaPublicacion != null) {
				int idPublicacion = ultimaPublicacion.getIdPublicacion();
				String temporal = getServletContext().getRealPath("")+Constantes.RUTATemporal;
				String newPath = getServletContext().getRealPath("")+Constantes.RUTACarpetaFotosPublicacion+idPublicacion;
				
				FileHandler.MakeDir(newPath);
				File temporalFolder = new File(temporal);
				
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
						imagenDao.insert(imagen);
						listImagenes.add(imagen);
						
					}
					FileHandler.DeleteAllFiles(temporal);
					request.setAttribute("imagenes", listImagenes);
					request.getRequestDispatcher("/InicioAdmin.jsp").forward(request, response);
					
				}
				
				
			}
		}
	}
	
	private void deleteImageTmp(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String imagenString = fileHandler.getParameter("image");
		String temporal = getServletContext().getRealPath("")+Constantes.RUTATemporal;
		if(imagenString != null){
			String[] spl = imagenString.split("\\");
			FileHandler.MakeDir(temporal);
			File delete = FileHandler.IfExists(temporal, spl[1]);
			if(delete != null) {
				delete.delete();
			}
			
			File temporalFolder = new File(temporal);
			ArrayList<Imagen> listImagenes = new ArrayList<Imagen>();
			
			for(File file : temporalFolder.listFiles()) {
				
				Imagen imagen = new Imagen();
				imagen.setRutaImgPublicacion(Constantes.RUTATemporal+File.separator+file.getName());
				
			}
			resultMap.put("imagenes", listImagenes);
			resultMap.put("cantidadSubida", temporalFolder.listFiles().length);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(new Gson().toJson(resultMap)); // <----- AJAX RESPONDE SIN REDIRIGIR
		}
	}
	
	

	
}
