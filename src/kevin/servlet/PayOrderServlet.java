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

import kevin.model.CreditCard;
import kevin.model.Order;
import kevin.model.User;
import kevin.service.CreditCardService;
import kevin.service.OrderService;
import kevin.service.PaymentService;

/**
 * Servlet implementation class PayOrderServlet
 */
@WebServlet("/PayOrderServlet")
public class PayOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		int id = Integer.parseInt(request.getParameter("id"));
		long creditCardNo = Long.parseLong(request.getParameter("creditcard"));
		double amount = Double.parseDouble(request.getParameter("amount"));

		CreditCard creditCard = CreditCardService.getCreditCard(creditCardNo);

		Order order = OrderService.getOrder(id);

		if (order.isPayed()) {

			request.setAttribute("result", "You have already payed for this order");
			
		} else {

			if (!order.isAccepted()) {

				request.setAttribute("result", "Order is not accepted yet");

			} else {

				if (CreditCardService.isCreditCardExpired(creditCard)) {

					request.setAttribute("result", "Credit Card is Expired");

				} else {

					if (CreditCardService.isCreditLimitExeeded(amount, creditCard)) {

						request.setAttribute("result", "Credit Limit is too low to make the payment");

					} else {

						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String date = sdf.format(calendar.getTime());

						if (!PaymentService
								.savePayment(PaymentService.setPayment(user.getEmail(), creditCardNo, amount, date))
								.equals("Payment Successful")) {

							request.setAttribute("result", "Failed to make the payment");

						} else {

							request.setAttribute("result", OrderService.updateOrder(
									OrderService.setOrder("", "", -1, "", true, -1, false, creditCardNo), id));

						}

					}

				}

			}
			
		}

		request.getRequestDispatcher("order.jsp").forward(request, response);

	}

}
