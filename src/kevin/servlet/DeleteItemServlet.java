package kevin.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kevin.service.ItemService;

/**
 * Servlet implementation class DeleteItemServlet
 */
@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FILE_SERVER = "C:\\Users\\zenj8\\eclipse-workspace\\KEVIN\\WebContent";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("itemId"));

		String message = ItemService.deleteItem(id);
		String json = new Gson().toJson(message);

//		if (message.equals("Item Deleted")) {
//
//			String appPath = request.getServletContext().getRealPath("");
//			String imagePath = appPath + File.separator + ItemService.getItem(id).getImagePath().replaceAll("/", "\\");
//			System.out.println(imagePath);
//			File image = new File(imagePath);
//			image.delete();
//			
//		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

}
