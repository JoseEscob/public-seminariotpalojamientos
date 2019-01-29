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
import exceptions.ValidacionException;
import modelo.Usuario;

/**
 * Servlet implementation class UploadFilesServlet
 */
@WebServlet("/UploadFilesServlet")
@MultipartConfig
public class UploadFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String paginaJsp = "";
	private Usuarios usuarioDao = new Usuarios();
       
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

			FileHandler fileHandler = new FileHandler(request,  getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser);
			List<FileItem> parametros = new ArrayList<FileItem>();
			List<FileItem> archivos = new ArrayList<FileItem>();
			
			for(FileItem item : fileHandler.getFormItems()) {

				if(item.isFormField())
					parametros.add(item);
				else
					archivos.add(item);
			}
			
			for(FileItem item : parametros) {
				if(item.getFieldName().compareTo(Constantes.accionPOST)==0) {
					System.out.println(item.getFieldName());

					switch(item.getString()) {
					case "cargarNuevasImagenes":
						break;
					case "cambiarImagen":
						cambiarImagen(request, response, parametros, archivos);
						break;
					case "verImagenes":
						
						break;
					case "cargarImagen":
						System.out.println("cargarImagen");
						cargarImagen(request, response, parametros, archivos);
						break;
					}
					break;
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void cargarImagen(HttpServletRequest request, HttpServletResponse response, List<FileItem> parametros, List<FileItem> archivos) throws Exception {
		

		int idUsuario = 3;
		String ruta =  getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser + idUsuario;
		for(FileItem item : archivos) {
			
			String fileName = new File(item.getName()).getName();
			
			String newName = "fotoUsuario_"+idUsuario + "." + FilenameUtils.getExtension(fileName);

			String filePath = ruta + File.separator + newName;
			
			File storeFile = new File(filePath);
			File sFile = new File(Constantes.RUTAFolderFotoUser + idUsuario +File.separator+storeFile.getName());
			usuarioDao.updateRutaFotoPerfil(idUsuario, sFile.getPath());
			request.setAttribute("imagen", sFile.getPath());
			item.write(storeFile);
		}
		paginaJsp ="/Test.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
	
	private void cambiarImagen(HttpServletRequest request, HttpServletResponse response, List<FileItem> parametros, List<FileItem> archivos) throws Exception {
		
		String idUsuarioString = null;
		/*for(FileItem item : parametros) {
			if(item.getFieldName().compareTo("sessionUSer") == 0) {
				idUsuarioString = item.getString();
				break;
			}
		}*/
		if(ORSesion.sesionActiva(request)) {
			idUsuarioString = Integer.toString(ORSesion.getUsuarioBySession(request).getIdUsuario());
		}
		
		Usuario objUsuario = ORSesion.getUsuarioBySession(request);
		
		
		int idUsuario = Integer.parseInt(idUsuarioString);
		String rutaActual = usuarioDao.getRutaFotoPerfil_Usuario(idUsuario);
		
		String ruta =  getServletContext().getRealPath("") + Constantes.RUTAFolderFotoUser + idUsuario;
		
		File imagen = new File( getServletContext().getRealPath("") + rutaActual);
		if(imagen.exists()) {
			
			for(FileItem item : archivos) {
				
				String fileName = new File(item.getName()).getName();
				
				String newName = "fotoUsuario_"+idUsuario + "." + FilenameUtils.getExtension(fileName);

				String filePath = ruta + File.separator + newName;
				
				File storeFile = new File(filePath);
				File sFile = new File(Constantes.RUTAFolderFotoUser + idUsuario +File.separator+storeFile.getName());
				usuarioDao.updateRutaFotoPerfil(idUsuario, sFile.getPath());
				request.setAttribute("imagen", sFile.getPath());
				item.write(storeFile);
				
			}
			
		}
		
		objUsuario = usuarioDao.get(objUsuario);
		
		ORSesion.nuevaSesion(request, objUsuario);
		
		request.setAttribute("objUsuario", ORSesion.getUsuarioBySession(request));
		paginaJsp = "/UsuarioViewModif.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
	

}
