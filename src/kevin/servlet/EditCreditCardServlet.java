package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.CreditCardService;

/**
 * Servlet implementation class EditCreditCardServlet
 */
@WebServlet("/EditCreditCardServlet")
public class EditCreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long creditCardNo = Long.parseLong(request.getParameter("ind"));
		double creditLimit = Double.parseDouble(request.getParameter("val"));
		
		request.setAttribute("result", CreditCardService.updateCreditCard(creditCardNo, creditLimit));
		request.getRequestDispatcher("card.jsp").forward(request, response);
		
	}

}
