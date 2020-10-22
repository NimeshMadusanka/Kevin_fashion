package kevin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.Review;
import kevin.service.ReviewService;

/**
 * Servlet implementation class ReviewReportServlet
 */
@WebServlet("/ReviewReportServlet")
public class ReviewReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String startDate, endDate;
		
		startDate = request.getParameter("startdate");
		endDate = request.getParameter("enddate");
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews = ReviewService.getReviews(startDate, endDate);
		
		if (reviews.isEmpty()) {
			
			request.setAttribute("result", "There are no Reviews in the specified period");
			request.getRequestDispatcher("adminreview.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("reviews", reviews);
			request.setAttribute("startdate", startDate);
			request.setAttribute("endDate", endDate);
			request.getRequestDispatcher("reviewreport.jsp").forward(request, response);
			
		}
		
	}

}
