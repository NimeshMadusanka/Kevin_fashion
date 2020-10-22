package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.User;
import kevin.service.DisputeService;
import kevin.service.OrderService;

/**
 * Servlet implementation class EditDisputeServlet
 */
@WebServlet("/EditDisputeServlet")
public class EditDisputeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		
		request.setAttribute("result", DisputeService.updateDispute(DisputeService.setDispute(null, description, null, null), id)); 
		request.getRequestDispatcher("dispute.jsp").forward(request, response);
		
	}

}
