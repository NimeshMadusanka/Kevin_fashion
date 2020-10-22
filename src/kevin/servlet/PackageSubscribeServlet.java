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
import kevin.service.CreditCardService;
import kevin.service.PackageService;
import kevin.service.PaymentService;
import kevin.service.UserService;

/**
 * Servlet implementation class PackageSubscribeServlet
 */
@WebServlet("/PackageSubscribeServlet")
public class PackageSubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") != null) {

			String userId = ((User) session.getAttribute("user")).getEmail();
			int packageId = Integer.parseInt(request.getParameter("pack"));
			long creditCard = Long.parseLong(request.getParameter("card"));

			kevin.model.Package pack = PackageService.getPackage(packageId);
			CreditCard card = CreditCardService.getCreditCard(creditCard);
			
			if (((User) session.getAttribute("user")).getPackageId() != packageId) {

				if (CreditCardService.isCreditCardExpired(card)) {

					request.setAttribute("result", "Credit Card is expired");

				} else {

					if (CreditCardService.isCreditLimitExeeded(pack.getPrice(), card)) {

						request.setAttribute("result", "Credit limit is too low");

					} else {

						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String date = sdf.format(calendar.getTime());

						request.setAttribute("result", PackageService.subscribe(userId, creditCard, packageId));
						PaymentService.savePayment(
								PaymentService.setPayment(userId, card.getCardNo(), pack.getPrice(), date));

						session.setAttribute("user", UserService.getUser(userId));

					}

				}

			} else {
				
				request.setAttribute("result", "You are already Subscribed to this package");
				
			}

		}

		request.getRequestDispatcher("pack.jsp").forward(request, response);

	}

}
