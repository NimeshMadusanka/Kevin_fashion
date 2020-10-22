package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Refund;
import kevin.service.RefundService;

/**
 * Servlet implementation class DeleteRefundServlet
 */
@WebServlet("/DeleteRefundServlet")
public class DeleteRefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		int id = Integer.parseInt(request.getParameter("id"));
		String url = "refund.jsp";
		
		if (session.getAttribute("admin") != null) {
			
			url = "adminrefund.jsp";
			request.setAttribute("result", RefundService.deleteRefund(id));
			
		} else {
			
			if (RefundService.getRefund(id).getStatus() == Refund.ACCEPTED)
				request.setAttribute("result", "Cannot Delete Request, It\'s Already Accepted");
			else
				request.setAttribute("result", RefundService.deleteRefund(id));
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
