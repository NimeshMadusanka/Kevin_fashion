package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kevin.service.ItemService;

/**
 * Servlet implementation class SizeServlet
 */
@WebServlet("/SizeServlet")
public class SizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("addsize") != null) {
			
			String message = ItemService.addSize(request.getParameter("addsize"));
			String json = new Gson().toJson(message);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}
		
		if (request.getParameter("delsize") != null) {

			String message = ItemService.deleteSize(request.getParameter("delsize"));
			String json = new Gson().toJson(message);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}
		
	}

}
