package kevin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.User;
import kevin.service.MessageService;

/**
 * Servlet implementation class SendMessagesServlet
 */
@WebServlet("/SendMessagesServlet")
public class SendMessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String message = request.getParameter("message");
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String messageDateTime = sdf.format(calendar.getTime());
		
		if (session.getAttribute("user") == null) {

			String userId = request.getParameter("userId");
			MessageService.saveUserMessage(MessageService.setUserMessage(userId, message, messageDateTime, true, false, false));

		} else {
			
			User user = (User) session.getAttribute("user");
			MessageService.saveUserMessage(MessageService.setUserMessage(user.getEmail(), message, messageDateTime, false, false, false));
			
		}
		
	}

}
