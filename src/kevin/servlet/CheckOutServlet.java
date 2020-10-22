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
import kevin.model.User;
import kevin.service.CartService;
import kevin.service.CreditCardService;
import kevin.service.PackageService;
import kevin.service.PaymentService;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") != null) {

			User user = (User) session.getAttribute("user");
			double total;
			long creditCard;

			creditCard = Long.parseLong(request.getParameter("card"));
			total = Double.parseDouble(request.getParameter("total"));
			
			CreditCard card = CreditCardService.getCreditCard(creditCard);
			
			if (CreditCardService.isCreditCardExpired(card)) {
				
				request.setAttribute("result", "Your Credit Card is Expired");
				
			} else {
				
				if (CreditCardService.isCreditLimitExeeded(total, card)) {
					
					request.setAttribute("result", "Your Credit Limit is too low");
					
				} else {
					
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					String date = sdf.format(calendar.getTime());
					
					if (user.getPackageId() != 0) {
						
						total = total * ((100 - PackageService.getPackage(user.getPackageId()).getDiscountPercentage()) / 100.0);
						
					}
					
					PaymentService.savePayment(PaymentService.setPayment(user.getEmail(), creditCard, total, date));
					CartService.clearCart(user.getEmail());
					request.setAttribute("result", "Purchase Successful");
					
				}
				
			}

		}
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);
		
	}

}
