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

import kevin.model.Review;
import kevin.model.User;
import kevin.service.ReviewService;

/**
 * Servlet implementation class EditReviewServlet
 */
@WebServlet("/EditReviewServlet")
public class EditReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String url = null;
		
		if (request.getParameter("userId") != null) {

			request.setAttribute("result", ReviewService.removeReply(request.getParameter("userId")));
			url = "adminreview.jsp";
			
		} else if (session.getAttribute("user") != null) {

			int rating = 0;
			String description;
			User user = (User) session.getAttribute("user");

			if (request.getParameter("rating") != null)
				rating = Integer.parseInt(request.getParameter("rating"));
			
			description = request.getParameter("description");
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = sdf.format(calendar.getTime());
			
			Review review = ReviewService.setReview(user.getEmail(), rating, description, dateTime);
			String validationResult = ReviewService.validateReviewEdit(review);
			
			if (validationResult.equals("Review Updated"))
				session.setAttribute("review", ReviewService.updateReview(ReviewService.getReview(user.getEmail()), review));

			request.setAttribute("result", validationResult);
			url = "feedback.jsp";
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
