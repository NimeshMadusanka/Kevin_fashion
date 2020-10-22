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
 * Servlet implementation class ColorServlet
 */
@WebServlet("/ColorServlet")
public class ColorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("addname") != null) {
			
			String message = ItemService.addColor(request.getParameter("addvalue"), request.getParameter("addname"));
			String json = new Gson().toJson(message);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}
		
		if (request.getParameter("delcolor") != null) {

			String message = ItemService.deleteColor(request.getParameter("delcolor"));
			String json = new Gson().toJson(message);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}
		
		if (request.getParameter("edtcolor") != null && request.getParameter("newname") != null) {

			String message = ItemService.editColor(ItemService.getColorId(request.getParameter("edtcolor")), request.getParameter("newname"));
			String json = new Gson().toJson(message);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			
		}
		
	}

}
