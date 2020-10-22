package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.service.DisputeService;
import kevin.service.OrderService;

/**
 * Servlet implementation class DeleteDisputeServlet
 */
@WebServlet("/DeleteDisputeServlet")
public class DeleteDisputeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String url = "dispute.jsp";

		if (!request.getParameter("id").equals("") || request.getParameter("id") != null) {

			int id = Integer.parseInt(request.getParameter("id"));

			if (session.getAttribute("admin") != null) {

				url = "admindispute.jsp";
				request.setAttribute("result", DisputeService.deleteDispute(id));

			} else {

				request.setAttribute("result", DisputeService.deleteDispute(id));

			}

		} else {

			request.setAttribute("result", "Please Select An Complaint");

		}

		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
