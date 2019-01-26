package controllersServlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

		
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
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				
		if(isMultipart) {
			System.out.println("1");

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MemoryThreshold);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload  = new ServletFileUpload(factory);
			upload.setFileSizeMax(MaxFileSize);
			upload.setSizeMax(MaxRequestSize);
			
			String projectName = this.getServletContext().getContextPath();
			String uploadPath = new File(System.getProperty("user.home")).getPath()+File.separator+"git"+File.separator+"seminariotpalojamientos" + projectName + File.separator + UploadDirectory;
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> formItems = upload.parseRequest(request);
				int index = -1;
				String newName = "";
				System.out.println("2");
				
				
				
				if(formItems != null && formItems.size() > 0) {
					System.out.println("3");

					for(FileItem item : formItems) {
						System.out.println("4");
						if(item.isFormField()) {
							String fieldname = item.getFieldName();
							String value = item.getString();
							
							newName = value;
						}else{
						
							index = formItems.indexOf(item);
						}
					}
				}
				String fileName = new File(formItems.get(index).getName()).getName();
				
				newName += "." + FilenameUtils.getExtension(fileName);
				String filePath = uploadPath + File.separator + newName;
				
				File storeFile = new File(filePath);
				System.out.println(filePath);
				formItems.get(index).write(storeFile);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		request.getRequestDispatcher("/Test.jsp").forward(request, response);
	}
		
}


