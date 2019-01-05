package controllersServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import modelo.Favorito;
import modelo.Localidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonParser;

import controladoresDAO.Localidades;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost - TestServlet");
		// TODO Auto-generated method stub
		
		Localidades localidadDao = new Localidades();
			
		// DEBE REALIZARSE EN ALGUNA FUNCION QUE NO HAGA REDIRIGIR LA PAGINA A OTRA.

		String r = (String)request.getParameter("id");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Favorito> datos = new ArrayList<Favorito>();
		
		
		String coso = request.getParameter("accionPublicacion");
		
		switch(coso) {
		case "getLocalidades":
			
			ArrayList<Localidad> localidades = new ArrayList<Localidad>();
			int idPartido = Integer.parseInt(request.getParameter("cmbPartido"));
			localidades = localidadDao.getByIdPartido(idPartido);
			
			if(localidades == null) {
				System.out.println("localidades : NULL");
			}
			
			resultMap.put("localidades", localidades);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(new Gson().toJson(resultMap));
			break;
		default:break;
		}
		
		
		
		
		
		
		
		
		
	
		
	}

}
