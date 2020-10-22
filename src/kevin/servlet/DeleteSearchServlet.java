package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.SearchService;

/**
 * Servlet implementation class DeleteSearchServlet
 */
@WebServlet("/DeleteSearchServlet")
public class DeleteSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id =  Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("result", SearchService.clearSearch(id));
		request.getRequestDispatcher("history.jsp").forward(request, response);
		
	}

}
