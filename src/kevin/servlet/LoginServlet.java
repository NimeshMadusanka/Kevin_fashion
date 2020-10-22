package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Admin;
import kevin.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!request.getParameter("email").equals("admin")) {

			String email, password;

			email = request.getParameter("email");
			password = request.getParameter("password");

			boolean validationResult = UserService.validateLogin(email, password);

			if (validationResult) {

				HttpSession session = request.getSession(true);
				session.setAttribute("user", UserService.getUser(email));

				request.getRequestDispatcher("account.jsp").forward(request, response);

			} else {

				request.setAttribute("result", "Invalid E-Mail or Password");
				request.getRequestDispatcher("account.jsp").forward(request, response);

			}

		} else {
			
			if (Admin.getAdmin().getPassword().equals(request.getParameter("password"))) {
				
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", Admin.getAdmin());
				
			} else {
				
				request.setAttribute("result", "Invalid E-Mail or Password");
				
			}
			
			request.getRequestDispatcher("admin.jsp").forward(request, response);
				
		}

	}

}
