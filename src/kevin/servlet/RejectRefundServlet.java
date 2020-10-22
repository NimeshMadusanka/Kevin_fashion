package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.Refund;
import kevin.service.RefundService;

/**
 * Servlet implementation class RejectRefundServlet
 */
@WebServlet("/RejectRefundServlet")
public class RejectRefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (RefundService.getRefund(id).getStatus() == Refund.ACCEPTED) {
			
			request.setAttribute("result", "Refund is Already Allowed");
			
		} else {
			
			request.setAttribute("result", RefundService.rejectRefund(id));
			
		}
		
		request.getRequestDispatcher("adminrefund.jsp").forward(request, response);
		
	}

}
