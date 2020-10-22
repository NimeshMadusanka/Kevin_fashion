package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.User;
import kevin.service.SearchService;

/**
 * Servlet implementation class ClearSearchServlet
 */
@WebServlet("/ClearSearchServlet")
public class ClearSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("user") != null) {
			
			User user = (User) session.getAttribute("user");
			request.setAttribute("result", SearchService.clearSearch(user.getEmail()));
			
		} else {
			
			request.setAttribute("result", "Please login to clear search");
			
		}
		
		request.getRequestDispatcher("history.jsp").forward(request, response);
		
	}

}
