package controllersServlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import extra.Constantes;
import extra.FileHandler;
import extra.LOG;
import modelo.Publicacion;
import modelo.Imagen;
import controladoresDAO.Publicaciones;
import exceptions.ServidorException;
import controladoresDAO.Imagenes;

		
/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
@MultipartConfig
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UploadDirectory = "ruta";
	private static final int MemoryThreshold = 1024 * 1024 * 3;
	private static final int MaxFileSize = 1024 * 1024 * 10;
	private static final int MaxRequestSize = 1024 * 1024 * 20;
	private Imagenes imagenDao = new Imagenes();
	private Publicaciones publicacionDao = new Publicaciones();
	private String paginaJsp = "";
       
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
			String accionPOST = request.getParameter(Constantes.accionPOST);
			if (accionPOST == null) {
				throw new ServidorException("NULL Param: " + Constantes.accionPOST + " en TestServlet");
			}
			LOG.info(String.format("%s POST: %s", Constantes.logJSPAccion, accionPOST));
			switch (accionPOST) {
				case "cargarNuevasImagenes":
					cargarNuevasImagenes(request, response);
					break;
				case "cambiarImagen":
					cambiarImagen(request, response);
					break;
				case "verImagenes":
					
					break;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void cargarNuevasImagenes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			
			String p = request.getParameter("cargarNuevasImagenes");
			int idp = Integer.parseInt(p);
			Publicacion publicacion = publicacionDao.getObjectByID(idp);
			if(publicacion != null) {
				String ruta = Constantes.RUTACarpetaFotosPublicacion+publicacion.getIdPublicacion();
				
				String uploadPath = getServletContext().getRealPath("") + File.separator + ruta;
				
				FileHandler fh = new FileHandler(request, uploadPath);
				
				int cantImagenes = fh.uploadFiles();
				request.setAttribute("message", "Cantidad de imagenes guardadas: "+cantImagenes);
				
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*ArrayList<String> imagenes = new ArrayList<String>();
		for(File file : FileHandler.getFiles(FileHandler.getUploadPath(getServletContext(), ruta))) {
		
			imagenes.add(Constantes.RUTACarpetaFotosPublicacion+"3"+File.separator+file.getName());
			
		}
		request.setAttribute("imagenes", imagenes);*/

		paginaJsp ="/Test.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
	
	private void cambiarImagen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		paginaJsp ="/Test.jsp";
		request.getRequestDispatcher(paginaJsp).forward(request, response);
	}
		
}




/*
 * 		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				
		if(isMultipart) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MemoryThreshold);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload  = new ServletFileUpload(factory);
			upload.setFileSizeMax(MaxFileSize);
			upload.setSizeMax(MaxRequestSize);	
			
			String uploadPath = getServletContext().getRealPath("")+ File.separator + UploadDirectory;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			for(File i : uploadDir.listFiles()) {
				i.delete();
			}
			
			try {
				List<FileItem> formItems = upload.parseRequest(request);
				int index = 0;
				
				
				
				if(formItems != null && formItems.size() > 0) {

					for(FileItem item : formItems) {
						if(item.isFormField()) {
						
							
						}else{
						
							
							index++;
							String fileName = new File(item.getName()).getName();
							
							String newName = index + "." + FilenameUtils.getExtension(fileName);
							String filePath = uploadPath + File.separator + newName;
							
							File storeFile = new File(filePath);
							System.out.println(storeFile.getParentFile().getName()+File.separator+storeFile.getName());
							File sFile = new File(storeFile.getParentFile().getName()+File.separator+storeFile.getName());
							item.write(sFile);
						}
					}
				}
	
				request.setAttribute("message", "Cantidad de imagenes guardadas: "+index);
				
				File folder = new File(uploadPath);
				File[] images = folder.listFiles();
				ArrayList<String> rutas = new ArrayList<String>();
				for(File file : images) {
					rutas.add(file.getParentFile().getName()+File.separator+file.getName());
					
				}
				request.setAttribute("imagenes", rutas);
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
 