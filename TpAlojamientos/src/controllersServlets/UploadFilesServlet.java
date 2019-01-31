package controllersServlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import extra.Constantes;
import extra.FileHandler;
import extra.ORSesion;
import controladoresDAO.Usuarios;
import controladoresDAO.Imagenes;
import modelo.Usuario;
import modelo.Imagen;

/**
 * Servlet implementation class UploadFilesServlet
 */
@WebServlet("/UploadFilesServlet")
@MultipartConfig
public class UploadFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = "";
	private Usuarios usuarioDao = new Usuarios();
	private Imagenes imagenDao = new Imagenes();
	private List<FileItem> params = null;
	private List<FileItem> files = null;
	private FileHandler fileHandler;
       
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
			Initialize(request);

			switch(getParameter(Constantes.accionPOST)) {

				case "cargarImagenesPublicacion":
					cargarImagenesPublicacion(request, response);
					break;
				case "cambiarImagenUsuario":
					cambiarImagenUsuario(request, response);
					break;
				case "verImagenes":
					
					break;
				case "cargarImagen":
					cargarImagen(request, response);
					break;
				default: break;
			}

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getPathFotoUsuario(FileItem item, int idUsuario) {
		this.makeDir(getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser + idUsuario);
		return getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser + idUsuario + File.separator + "fotoUsuario_"+idUsuario + "." + FilenameUtils.getExtension(new File(item.getName()).getName());

	}
	private String getPathFotosPublicaciones(FileItem item, int idPublicacion, int count) {
		this.makeDir(getServletContext().getRealPath("") + Constantes.RUTACarpetaFotosPublicacion + idPublicacion);
		return getServletContext().getRealPath("") + Constantes.RUTACarpetaFotosPublicacion + idPublicacion + File.separator + count + "." + FilenameUtils.getExtension(new File(item.getName()).getName());
	}
	
	
	private String getParameter(String paramName) {

		String ret = null;
		for(FileItem item : this.params) {

			if(item.getFieldName().compareTo(paramName) == 0) {
				ret = item.getString();
				break;
			}
		}


		return ret;
	}
	private void makeDir(String pathNameDir) {
		
		File uploadDir = new File(pathNameDir);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
	}
	
	private void Initialize(HttpServletRequest request) throws Exception {

		this.fileHandler = new FileHandler(request);
		this.params = new ArrayList<FileItem>();
		this.files = new ArrayList<FileItem>();
		
		for(FileItem item : this.fileHandler.getFormItems()) {

			if(item.isFormField())
				this.params.add(item);
			else
				this.files.add(item);
		}
	}

	
	private void cargarImagen(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//	getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser

		int idUsuario = 3;
		FileItem item = this.files.get(0);
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
				imagen.delete();
				FileItem item = this.files.get(0);
				File storeFile = new File( getPathFotoUsuario(item, idUsuario));
				File sFile = new File(Constantes.RUTAFolderFotoUser + idUsuario +File.separator+storeFile.getName());
				usuarioDao.updateRutaFotoPerfil(idUsuario, sFile.getPath());
				request.setAttribute("imagen", sFile.getPath());
				item.write(storeFile);
				objUsuario = usuarioDao.get(objUsuario);
				ORSesion.nuevaSesion(request, objUsuario);
				
			}
			
			request.setAttribute("objUsuario", ORSesion.getUsuarioBySession(request));
			paginaJsp = "/UsuarioViewModif.jsp";
		}
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
	
	private void cargarImagenesPublicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//if(ORSesion.sesionActiva(request)) {
			ArrayList<Imagen> listImagenes = new ArrayList<Imagen>();
			String idPublicacionString = getParameter("idPublicacion");
			if(idPublicacionString != null) {
				int idPublicacion = Integer.parseInt(idPublicacionString);
				int contador = 0;
				for(FileItem item : this.files) {
					contador ++;
					//Guardamos el archivo en el servidor
					File storeFile = new File(getPathFotosPublicaciones(item, idPublicacion, contador));
					item.write(storeFile);
					
					//Ahora damos el alta en la base de datos
					Imagen imagen = new Imagen();
					imagen.setIdPublicacion(idPublicacion);
					imagen.setIdImagen(contador);
					imagen.setHabilitado(true);
					imagen.setRutaImgPublicacion(Constantes.RUTACarpetaFotosPublicacion + idPublicacion + File.separator + storeFile.getName());
					imagenDao.insert(imagen);
					listImagenes.add(imagen);
				}
				request.setAttribute("imagenes", listImagenes);
			}
		//}
		request.getRequestDispatcher("/InicioAdmin.jsp").forward(request, response);
	}
	
	

	
}
