package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.User;
import kevin.service.ReviewService;
import kevin.service.UserService;

/**
 * Servlet implementation class RemoveReviewServlet
 */
@WebServlet("/RemoveReviewServlet")
public class RemoveReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		boolean result = false;
		String url = null;
		
		if (request.getParameter("userId") != null) {
			
			result = ReviewService.removeReview(UserService.getUser(request.getParameter("userId")));
			url = "adminreview.jsp";
			
		} else if (session.getAttribute("user") != null) {
			
			User user = (User) session.getAttribute("user");
			result = ReviewService.removeReview(user);
			session.setAttribute("review", null);
			url = "feedback.jsp";
			
		}
		
		if (result)
			request.setAttribute("result", "Review Deletion Failed");
		else
			request.setAttribute("result", "Review Deleted");
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
