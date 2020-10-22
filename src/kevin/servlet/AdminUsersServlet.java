package kevin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Message;
import kevin.service.MessageService;

/**
 * Servlet implementation class AdminUsersServlet
 */
@WebServlet("/AdminUsersServlet")
public class AdminUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("userId") != null) {

			HttpSession messageSession = request.getSession(true);
			messageSession.setAttribute("messageuser", request.getParameter("userId"));
			
		}
		
		request.getRequestDispatcher("adminmessage.jsp").forward(request, response);
		
	}

}
