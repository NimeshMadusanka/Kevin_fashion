package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Refund;
import kevin.model.User;
import kevin.service.RefundService;

/**
 * Servlet implementation class RefundServlet
 */
@WebServlet("/RefundServlet")
public class RefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		int paymentId = Integer.parseInt(request.getParameter("payment"));
		String description = request.getParameter("description");
		
		request.setAttribute("result", RefundService.saveRefund(RefundService.setRefund(paymentId, description, Refund.PENDING, user.getEmail())));
		request.getRequestDispatcher("refund.jsp").forward(request, response);
		
	}

}
