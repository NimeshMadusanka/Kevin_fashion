package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.ReviewService;

/**
 * Servlet implementation class ReviewReplyServlet
 */
@WebServlet("/ReviewReplyServlet")
public class ReviewReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId, reply;
		
		userId = request.getParameter("email");
		reply = request.getParameter("reply");
		
		request.setAttribute("result", ReviewService.sendReply(userId, reply));
		request.getRequestDispatcher("adminreview.jsp").forward(request, response);
		
	}

}
