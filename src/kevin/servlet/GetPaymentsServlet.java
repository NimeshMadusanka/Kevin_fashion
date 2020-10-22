package kevin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import kevin.model.Order;
import kevin.model.Payment;
import kevin.model.User;
import kevin.service.OrderService;
import kevin.service.PaymentService;

/**
 * Servlet implementation class GetPaymentsServlet
 */
@WebServlet("/GetPaymentsServlet")
public class GetPaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		ArrayList<Payment> payments = PaymentService.getPayments(user.getEmail());
		String json = new Gson().toJson(payments);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
