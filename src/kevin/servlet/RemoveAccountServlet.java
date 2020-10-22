package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.User;
import kevin.service.UserService;

/**
 * Servlet implementation class RemoveAccountServlet
 */
@WebServlet("/RemoveAccountServlet")
public class RemoveAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		boolean result = false;
		
		if (session.getAttribute("user") != null) {
			
			User user = (User) session.getAttribute("user");
			result = UserService.removeUser(user.getEmail());
			session.removeAttribute("user");
			
		}
		
		if (result)
			request.setAttribute("result", "Account Deletion Failed");
		else
			request.setAttribute("result", "Account Deleted");
		
		request.getRequestDispatcher("account.jsp").forward(request, response);
		
	}

}
