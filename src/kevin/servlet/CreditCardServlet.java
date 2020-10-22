package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.CreditCard;
import kevin.model.User;
import kevin.service.CreditCardService;

/**
 * Servlet implementation class CreditCardServlet
 */
@WebServlet("/CreditCardServlet")
public class CreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long cardNo;
		int cvv;
		double creditLimit;
		String name, address, country, userId, network, expireDate;
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("user") != null) {
			
			cardNo = Long.parseLong(request.getParameter("cardno"));
			cvv = Integer.parseInt(request.getParameter("cvv"));
			creditLimit = Double.parseDouble(request.getParameter("creditlimit"));
			name = request.getParameter("name");
			address = request.getParameter("address");
			country = request.getParameter("country");
			userId = ((User)session.getAttribute("user")).getEmail();
			expireDate = request.getParameter("expiredate");
			network = request.getParameter("network");
			
			CreditCard creditCard = CreditCardService.setCreditCard(cardNo, userId, name, address, network, country, creditLimit, expireDate, cvv);
			String validationResult = CreditCardService.validateCreditCard(creditCard);
			if (validationResult.equals("Credit Card Validation Successful"))
				request.setAttribute("result", CreditCardService.saveCreditCard(creditCard));
			else
				request.setAttribute("result", validationResult);
			
		}
		
		request.getRequestDispatcher("card.jsp").forward(request, response);
		
	}

}
