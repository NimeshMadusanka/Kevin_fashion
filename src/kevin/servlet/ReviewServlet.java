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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") != null) {

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
			String validationResult = ReviewService.validateReview(review);
			
			if (validationResult.equals("Review Submitted")) {
				
				request.setAttribute("result", ReviewService.saveReview(review));
				session.setAttribute("review", ReviewService.getReview(user.getEmail()));
				
			} else {
				
				request.setAttribute("result", validationResult);
				
			}
			
		}
		
		request.getRequestDispatcher("feedback.jsp").forward(request, response);

	}

}
